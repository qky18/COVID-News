package com.java.qiukeyue.fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.java.qiukeyue.Manager;
import com.java.qiukeyue.R;
import com.java.qiukeyue.bean.CovidData;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ViewportChangeListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.PreviewColumnChartView;

// Instances of HomeFragment class are fragments representing a single type of collection
public class ChartFragment extends Fragment {
    private final String TAG;
    private Observer<List<CovidData>> observer = null;
    private SwipeRefreshLayout refreshLayout;

    // UI: for drawing charts
    private ColumnChartView chart;
    private ColumnChartData data;
    private PreviewColumnChartView previewChart;
    /**
     * Deep copy of data.
     */
    private ColumnChartData previewData;

    public ChartFragment(String tag){
        this.TAG = tag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_chart, container, false);
        refreshLayout = rootView.findViewById(R.id.swipe_refresh);
        chart = rootView.findViewById(R.id.chart);
        previewChart = rootView.findViewById(R.id.chart_preview);

        initView();
        initObserver();
        //initSwipeRefresh();

        return rootView;
    }

    private void initSwipeRefresh() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Manager.get_covid_data(observer, TAG.equals("China"));
            }
        });
    }

    private void initView() {

        // Disable zoom/scroll for previewed chart, visible chart ranges depends on preview chart viewport so
        // zoom/scroll is unnecessary.
        chart.setZoomEnabled(false);
        chart.setScrollEnabled(false);

        previewChart.setViewportChangeListener(new ViewportListener());

        previewX(true);
    }

    private void initObserver() {
        observer = new Observer<List<CovidData>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("Chart", "observer subscribed");
            }
            @Override
            public void onNext(List<CovidData> covidDataList) {
                //generateDefaultData();
                //chart.setColumnChartData(data);
                for(CovidData data: covidDataList){
                    Log.e("Chart", data.getName());
                }

                // Generate data for previewed chart and copy of that data for preview chart.
                generateData(covidDataList);
                chart.setColumnChartData(data);
                previewChart.setColumnChartData(previewData);
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onComplete() {
            }
        };
        Log.e("NewsCollection","Observer available");
        Manager.get_covid_data(observer, TAG.equals("China"));
    }

    private void generateData(List<CovidData> covidDataList) {
        List<Integer> color = new ArrayList<>();
        color.add(Color.parseColor("#009BDB"));
        color.add(Color.parseColor("#009BDB"));
        color.add(Color.parseColor("#009BDB"));
        color.add(Color.parseColor("#009BDB"));

        // All columns
        int numColumns = covidDataList.size();
        List<Column> columns = new ArrayList<>();
        // X, Y axis values
        List<AxisValue> axisXValues = new ArrayList<>();
        List<SubcolumnValue> values;
        for (int i=0; i<numColumns; i++) {
            CovidData data = covidDataList.get(i);
            values = new ArrayList<>();
            axisXValues.add(new AxisValue(i).setLabel(data.getName()));
                // sub columns(4): confirmed,
                values.add(new SubcolumnValue(data.getConfirmed(), ChartUtils.pickColor()));
                values.add(new SubcolumnValue(data.getSuspected(), ChartUtils.pickColor()));
                values.add(new SubcolumnValue(data.getDead(), ChartUtils.pickColor()));
                values.add(new SubcolumnValue(data.getCured(), ChartUtils.pickColor()));

            columns.add(new Column(values));
        }

        // init vertical data
        data = new ColumnChartData(columns);
        // init X, Y axis
        data.setAxisXBottom(new Axis(axisXValues));
        data.setAxisYLeft(new Axis().setHasLines(true));

        // prepare preview data, is better to use separate deep copy for preview chart.
        // set color to grey to make preview area more visible.
        previewData = new ColumnChartData(data);
        for (Column column : previewData.getColumns()) {
            for (SubcolumnValue value : column.getValues()) {
                value.setColor(ChartUtils.DEFAULT_DARKEN_COLOR);
            }
        }
    }

    private void generateDefaultData() {
        int numSubcolumns = 1;
        int numColumns = 50;
        // All columns
        List<Column> columns = new ArrayList<>();
        // X, Y axis values
        List<AxisValue> axisXValues = new ArrayList<>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<>();
            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue((float) Math.random() * 50f + 5, ChartUtils.pickColor()));
            }

            columns.add(new Column(values));
        }

        data = new ColumnChartData(columns);
        data.setAxisXBottom(new Axis());
        data.setAxisYLeft(new Axis().setHasLines(true));

        // prepare preview data, is better to use separate deep copy for preview chart.
        // set color to grey to make preview area more visible.
        previewData = new ColumnChartData(data);
        for (Column column : previewData.getColumns()) {
            for (SubcolumnValue value : column.getValues()) {
                value.setColor(ChartUtils.DEFAULT_DARKEN_COLOR);
            }
        }

    }

    private void previewX(boolean animate) {
        Viewport tempViewport = new Viewport(chart.getMaximumViewport());
        float dx = tempViewport.width() / 4;
        tempViewport.inset(dx, 0);
        if (animate) {
            previewChart.setCurrentViewportWithAnimation(tempViewport);
        } else {
            previewChart.setCurrentViewport(tempViewport);
        }
        previewChart.setZoomType(ZoomType.HORIZONTAL);
    }

    /**
     * Viewport listener for preview chart(lower one). in {@link #onViewportChanged(Viewport)} method change
     * viewport of upper chart.
     */
    private class ViewportListener implements ViewportChangeListener {

        @Override
        public void onViewportChanged(Viewport newViewport) {
            // don't use animation, it is unnecessary when using preview chart because usually viewport changes
            // happens to often.
            chart.setCurrentViewport(newViewport);
        }

    }
}