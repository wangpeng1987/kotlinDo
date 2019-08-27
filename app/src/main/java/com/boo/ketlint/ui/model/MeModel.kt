package  com.boo.ketlint.ui.model

import com.boo.ketlint.net2.domain.MeResult
import com.boo.ketlint.net2.domain.Result
import com.boo.ketlint.ui.contract.MeContract
import io.reactivex.Observable
import mvp.ljb.kt.model.BaseModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/07/08
 * @Description input description
 **/
class MeModel : BaseModel(), MeContract.IModel {

    override fun getAllMe(): Observable<MutableList<MeResult>> {
        var mes: MutableList<MeResult> = mutableListOf()

        mes.add(MeResult("https://b-gold-cdn.xitu.io/favicons/v2/apple-touch-icon.png", "https://juejin.im/", "掘金"))
        mes.add(MeResult("https://www.huxiu.com/public/logo-120.png", "https://m.huxiu.com/channel/2.html", "虎嗅创业"))
        mes.add(MeResult("https://csdnimg.cn/public/favicon.ico", "https://www.csdn.net/nav/mobile", "CSDN"))
        mes.add(MeResult("http://gank.io/static/images/special/work.png", "http://gank.io/api", "干货集中营"))
        mes.add(MeResult("https://cdn2.jianshu.io/assets/web/nav-logo-4c7bbafe27adc892f3046e6978459bac.png", "https://www.jianshu.com/c/3fde3b545a35", "简书"))

        mes.add(MeResult("https://www.kotlincn.net/assets/images/apple-touch-icon.png", "https://www.kotlincn.net/docs/reference/", "kotlin"))
        mes.add(MeResult("https://cdn.jsdelivr.net/gh/flutterchina/website@1.0/images/favicon.png", "https://flutterchina.club/setup-macos/", "flutter"))
        mes.add(MeResult("https://docs.unrealengine.com/Include/Images/site_icon.png", "https://docs.unrealengine.com/zh-CN/GettingStarted/index.html", "虚幻4"))
        mes.add(MeResult("https://atts.w3cschool.cn/attachments/cover/cover_unity3d_jc.png?t=1314520?imageView2/1/w/48/h/48", "https://www.w3cschool.cn/unity3d_jc/", "U3D官教"))
        mes.add(MeResult("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566898773009&di=bac20049f81388f829b744673e742dbd&imgtype=0&src=http%3A%2F%2Falcdn.img.xiaoka.tv%2F20171204%2F41d%2F199%2F0%2F41d1992b040bd53e66244bb088472c6a.jpg%401e_1c_0o_0l_600h_800w_90q_1pr.jpg", "https://www.ximalaya.com/jiaoyu/19944284/", "喜马拉雅"))

        mes.add(MeResult("https://apic.douyucdn.cn/upload/avatar_v3/201908/87111654727d46118d7832205c27fb27_big.jpg", "https://www.douyu.com/9595", "花老师"))
        mes.add(MeResult("https://www.baidu.com/img/superlogo_c4d7df0a003d3db9b65e9ef0fe6da1ec.png", "https://www.baidu.com/", "百度"))
        mes.add(MeResult("https://weibo.com/favicon.ico", "https://weibo.com/", "微博"))
        mes.add(MeResult("https://s3a.pstatp.com/toutiao/resource/ntoutiao_web/static/image/favicon_5995b44.ico", "https://m.toutiao.com/?W2atIF=1", "头条新闻"))
        mes.add(MeResult("https://mat1.gtimg.com/www/icon/favicon2.ico", "https://news.qq.com/", "腾讯新闻"))

        mes.add(MeResult("http://xiaoshuo.uc.cn/assets/website-name-new-for-uc_47b0b7c.png", "http://xiaoshuo.uc.cn/#!/source/mobile/ct/bookshelf/", "UC小说"))
        mes.add(MeResult("https://g.alicdn.com/shenma-project/sm_novel_pwa/static/img/novel152.png", "https://xiaoshuo.sm.cn/sc/1/channel/index?format=html&uc_param_str=dnntnwvepffrgibijbprsvdsdichei&from=smor&safe=1#/channel/index", "神马小说"))
        mes.add(MeResult("https://static.zhihu.com/static/revved/img/ios/touch-icon-152.87c020b9.png", "https://www.zhihu.com/topics", "知乎"))
        mes.add(MeResult("", "", ""))
        mes.add(MeResult("", "", ""))

        mes.add(MeResult("http://pic05.babytreeimg.com/img/header_footer/logo-201610.png", "http://www.babytree.com/", "宝宝树"))
        mes.add(MeResult("https://static-mmb.mmbang.info/mmbang/logo/logo_l.png?x-oss-process=image/resize,w_148", "https://www.mmbang.com/", "妈妈帮"))
        mes.add(MeResult("http://www.ci123.com/index/styles/images/2018NYlogo.gif", "http://m.ci123.com/", "育儿网"))
        mes.add(MeResult("", "", ""))
        mes.add(MeResult("", "", ""))

        mes.add(MeResult("https://static.meishichina.com/v6/img/lib/wapico.png", "https://m.meishichina.com/", "美食天下"))
        mes.add(MeResult("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=1242270212,1350650826&fm=58&bpow=305&bpoh=283", "http://web.breadtrip.com/", "面包旅行"))
        mes.add(MeResult("", "", ""))
        mes.add(MeResult("", "", ""))
        mes.add(MeResult("", "", ""))

        mes.add(MeResult("", "http://www.bizsofts.com/", "软件商务网"))
        mes.add(MeResult("", "http://www.taskcity.com/", "智诚"))
        mes.add(MeResult("", "", ""))
        mes.add(MeResult("", "", ""))
        mes.add(MeResult("", "", ""))
        return Observable.just(mes)
    }
}

