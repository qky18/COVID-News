package com.java.qiukeyue.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.java.qiukeyue.MainActivity;
import com.java.qiukeyue.NewsViewActivity;
import com.java.qiukeyue.R;
import com.java.qiukeyue.adapter.NewsFragmentAdapter;
import com.java.qiukeyue.bean.News;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

// Instances of MainCollection class are fragments representing a single type of collection
public class NewsCollectionFragment extends Fragment implements
        NewsFragmentAdapter.OnNewsSelectedListener {
    private final int NUM_SIZE = 20;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        List<News> newsList = new LinkedList<>();
        News test = new News();
        test.setTitle("校长邱勇出席泰晤士高等教育2020年世界学术峰会并致辞");
        test.setDate("2020-9-4");
        test.setSource("清华新闻网");
        test.setContent("清华新闻网9月4日电（记者 詹萌 刘书田 摄影 许德刚）北京时间9月1日下午，清华大学校长邱勇在泰晤士高等教育2020年世界学术峰会发表了题为“复兴与希望：后疫情时代高等教育的趋势”的视频致辞。清华大学副校长、教务长杨斌参加专题讨论会并讲话。会议由泰晤士高等教育首席知识官费尔·巴蒂（Phil Baty）主持。\n邱勇表示，2020年是历史的分水岭，也是高等教育的“新曙光”。疫情使我们坚定地认识到，与各方合作伙伴保持制度化联系至关重要。在为期两天的在线会议中，多所世界顶尖高校领导及企业机构专家负责人围绕新冠疫情的应对经验以及未来高等教育的展望进行了讨论。\n邱勇指出，最近高等教育发生了诸多变化，包括移动互联的加速发展、技术的进步、个性化的课堂等。这场疫情将成为高等教育更深刻变革的催化剂，而这场我们称之为“新曙光”的变革对于世界高等教育而言意味着复兴和希望。\n对于后疫情时代的高等教育，邱勇提出了四个方面的展望：\n第一，后疫情时代的大学，一定是更加开放融合的大学。大学教育将突破课堂内外、校内外、国内外、线上线下的物理边界、技术和身份限制，并肩负起更大的使命和责任。\n第二，后疫情时代的学习，一定是更加自主的学习。教师和学生都将拥有更高程度的独立自主性，网络教育资源将更大限度地开放共享，使学生能够成为“学习的主人”。\n第三，后疫情时代的教育，一定是更加智慧的教学和更加普惠的高质量教育。疫情期间普遍开展的在线学习促进了教育教学改革：智能教学工具将增加师生互动，给所有学生“前排的座位”；教育大数据将有助于更好地了解学习者的行为；智慧教学可以使学习更加个性化，实现对学生的因材施教。进一步的努力将缩小“数字鸿沟”，创造更多可利用的优质资源，促进教育公平。\n第四，后疫情时代的世界一流大学，一定会实现更加有力的引领。这场全球公共卫生危机以及大学在协调应对中发挥的作用，将进一步加强世界一流大学的影响力和对更广泛的高等教育发展的溢出效应。大学在应对全球挑战方面的作用将更加突出。\n最后，邱勇对清华所有合作伙伴及泰晤士高等教育全球网络表示感谢。他表示，通过大家的携手努力，在这场前所未有的挑战面前，我们终将看到复兴和希望。\n在9月2日的“这是高等教育的新曙光吗？”专题讨论会上，杨斌发表讲话。杨斌表示，目前高等教育领域出现了许多积极的变化。得益于技术的创新和师生的共同努力，疫情之下全球高等教育作为一个整体，向实现融合式教学迈出了一大步。在线教学技术和智能教学工具弥补了面对面交流的缺失，提高了课堂的参与度和互动性，让学习更个性化、更包容，能更好地实现因材施教。同时，在线教育使教育资源变得更易获得。大学和课堂将以更加开放和透明的方式运行，使得更广泛的社会群体能够走进大学课堂，参与到对教学内容和教学方法的评价当中。\n\n杨斌出席在线会议讨论\n杨斌强调，对于世界各地的大学来说，当下无疑是一个好的时机，去审视我们如何为全球稳定和应对全球挑战做出贡献。许多大学已经承担起社会责任，并积极参与抗击疫情的一线工作，包括疫苗开发、社会研究和社区服务等。\n杨斌认为，教育的理想和目标是为所有不同阶层、不同种族的人提供自由的学习机会。因此，高质量的教育将更加以学习者为导向，未来的教育必须具有更大的包容性，同时保证更高的质量，先进的技术也将帮助更多的学习者享受到清华的课程资源。\n出席本场会议的其他高校嘉宾也纷纷表达了对高等教育的看法。多伦多大学副教务长苏珊·麦卡汉（Susan McCahan）表示，虚拟课堂与现实课堂的结合，将使更多学生通过网络获得教育机会和教育资源，但也可能加剧教育的不平等。如何利用网络促进平等、减少不平等，并且重新认识线上线下混合教育模式中的学生身份认同，是今后十分重要的任务。\n悉尼大学副校长、副教务长理查德·迈尔斯（Richard Miles）认为，大学教育对学生个人能力的培养和锻炼至关重要，不同于面对面的传统学习方式，混合教学模式能否对学生的学习能力、解决问题能力、批判性思维能力等方面的培养起到良好效果值得关注。\n纽约大学副教务长克莱·舍基（Clay Shirky）表示，疫情引发的教学方式转变将给各国带来许多新的挑战，“大学学位背后的学生能力培养是否到位”、“全球化时代下东西方大学的联系将更紧密还是更疏离”、“在线教育如何影响大学规模”等问题都需要我们深入思考。\n此外，泰晤士高等教育世界学术峰会还就后疫情时代的全球大学合作、数字化转型与大学教育的变革发展、高等教育未来将要面对的挑战及应对策略等议题进行了热烈的讨论。\n泰晤士高等教育世界学术峰会由英国高等教育报刊《泰晤士高等教育》主办，旨在分享和讨论全球大学和研究中的实践与创新进展。该峰会每年举行一次，汇集来自高等教育、研究、工业和政府的全球杰出思想领袖，为学术教育与研究界提供了学习、互动、创造和分享的平台。本次泰晤士高等教育世界学术峰会共有来自20多个国家的100余位代表进行线上交流发言，1000余名参会者在线参加会议。会议聚焦疫情给全球高等教育带来的挑战，以及后疫情时代高等教育改革发展面临的新机遇。\n\n");
        for (int i=0; i<NUM_SIZE; i++){
            newsList.add(test);
        }

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // Set adapter for recyclerView
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new NewsFragmentAdapter(NUM_SIZE, newsList, this));
        return rootView;
    }

    @Override
    public void onNewsSelected(News news) {
        // Go to the details page for the selected patient
        Intent intent = new Intent(getActivity(), NewsViewActivity.class);
        intent.putExtra("title", news.getTitle());
        intent.putExtra("content", news.getContent());
        startActivity(intent);
    }
}