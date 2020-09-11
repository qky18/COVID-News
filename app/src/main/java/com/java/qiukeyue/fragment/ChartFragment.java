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

import java.util.ArrayList;
import java.util.Comparator;
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

    @Override
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
        initSwipeRefresh();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Manager.get_covid_data(observer, TAG.equals("China"));
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

        previewX(false);
    }

    private void initObserver() {
        observer = new Observer<List<CovidData>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("Chart", "observer subscribed");
            }
            @Override
            public void onNext(List<CovidData> covidDataList) {
                // Generate data for previewed chart and copy of that data for preview chart.
                covidDataList.sort(new Comparator<CovidData>() {
                    @Override
                    public int compare(CovidData o1, CovidData o2) {
                        // descending order
                        return Integer.compare(o2.getConfirmed(), o1.getConfirmed());
                    }
                });
                generateData(covidDataList);
                chart.setColumnChartData(data);
                previewChart.setColumnChartData(previewData);
                previewX(false);
                refreshLayout.setRefreshing(false);
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onComplete() {
            }
        };
        Manager.get_covid_data(observer, TAG.equals("China"));
    }

    private float getValue(int value){
        return value > 1 ? (float) Math.log10(value) : value;
    }

    private void generateData(List<CovidData> covidDataList) {

        List<Integer> color = new ArrayList<>();
        color.add(ChartUtils.COLOR_BLUE);
        color.add(ChartUtils.COLOR_ORANGE);
        color.add(ChartUtils.COLOR_RED);
        color.add(ChartUtils.COLOR_GREEN);

        // All columns
        int numColumns = covidDataList.size();
        List<Column> columns = new ArrayList<>();
        // X, Y axis values
        List<AxisValue> axisXValues = new ArrayList<>();
        List<SubcolumnValue> values;
        int offset = 0;
        for (int i=0; i<numColumns; i++) {
            CovidData data = covidDataList.get(i);
            values = new ArrayList<>();
            //axisXValues.add(new AxisValue(i).setLabel(data.getName()));
            if(TAG.equals("China")) {
                axisXValues.add(new AxisValue(i).setLabel(data.getName()));
            } else {
                String label = Manager.getName(data.getName());
                Log.e("chart", data.getName() + " " + label);
                if (label == null) {
                    offset++;
                    continue;
                }
                if (label.length() == 4) {
                    axisXValues.add(new AxisValue(i-offset).setLabel("......." + label));
                } else if (label.length() == 3) {
                    axisXValues.add(new AxisValue(i-offset).setLabel("..." + label));
                } else {
                    axisXValues.add(new AxisValue(i-offset).setLabel(label));
                }
            }
                // sub columns(4): confirmed,
                values.add(new SubcolumnValue(getValue(data.getConfirmed()), color.get(0)).setLabel("确诊:" + data.getConfirmed()));
                values.add(new SubcolumnValue(getValue(data.getSuspected()), color.get(1)).setLabel("疑似:" + data.getSuspected()));
                values.add(new SubcolumnValue(getValue(data.getDead()), color.get(2)).setLabel("死亡:" + data.getDead()));
                values.add(new SubcolumnValue(getValue(data.getCured()), color.get(3)).setLabel("治愈:" + data.getCured()));

            columns.add(new Column(values).setHasLabelsOnlyForSelected(true));
            /*
            if(getValue(data.getSuspected()) < 0.2){
                columns.add(new Column(values).setHasLabels(true).setHasLabelsOnlyForSelected(false));
            } else{
                columns.add(new Column(values).setHasLabelsOnlyForSelected(true));
            }
             */
        }

        // init vertical data
        data = new ColumnChartData(columns);

        // prepare preview data, is better to use separate deep copy for preview chart.
        // set color to grey to make preview area more visible.
        previewData = new ColumnChartData(data);
        for (Column column : previewData.getColumns()) {
            for (SubcolumnValue value : column.getValues()) {
                value.setColor(ChartUtils.DEFAULT_DARKEN_COLOR);
            }
        }
        previewData.setAxisXBottom(new Axis(axisXValues));
        previewData.setAxisYLeft(new Axis().setHasLines(true).setName("人数"));

        // init X, Y axis
        data.setAxisXBottom(new Axis(axisXValues).setHasTiltedLabels(TAG.equals("World")).setTextColor(Color.BLACK));
        data.setAxisYLeft(new Axis().setHasLines(true).setName("人数(log)").setTextColor(Color.BLACK));
    }

    private void previewX(boolean animate) {
        Viewport tempViewport = new Viewport(-0.7f, chart.getMaximumViewport().height()*1.25f, 5, 0);
        float dx = tempViewport.width() / 4;
        tempViewport.inset(dx, 0);
        if (animate) {
            previewChart.setCurrentViewportWithAnimation(tempViewport);
        } else {
            previewChart.setCurrentViewport(tempViewport);
        }
        previewChart.setZoomType(ZoomType.HORIZONTAL);
    }

    private void previewXY() {
        // Better to not modify viewport of any chart directly so create a copy.
        Viewport tempViewport = new Viewport(-0.7f, chart.getMaximumViewport().height()*1.25f, 5, 0);
        // Make temp viewport smaller.
        float dx = tempViewport.width() / 4;
        float dy = tempViewport.height() / 4;
        tempViewport.inset(dx, dy);
        previewChart.setCurrentViewportWithAnimation(tempViewport);
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