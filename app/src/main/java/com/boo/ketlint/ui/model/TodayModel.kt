package  com.boo.ketlint.ui.model

import com.boo.ketlint.net.Category
import com.boo.ketlint.net2.http.IUserHttpProtocol
import com.boo.ketlint.ui.contract.TodayContract
import io.reactivex.Observable
import mvp.ljb.kt.model.BaseModel
import net.ljb.kt.client.HttpFactory

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/07/08
 * @Description input description
 **/
class TodayModel : BaseModel(), TodayContract.IModel {

    override fun getToday(page:Int): Observable<Category> {
        return HttpFactory.getProtocol(IUserHttpProtocol::class.java)
            .getToday()
    }
}

