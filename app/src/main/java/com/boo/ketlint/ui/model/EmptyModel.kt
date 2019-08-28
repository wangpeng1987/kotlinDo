package  com.boo.ketlint.ui.model

import com.boo.ketlint.net2.domain.Category
import com.boo.ketlint.net2.http.IUserHttpProtocol
import com.boo.ketlint.ui.contract.EmptyContract
import io.reactivex.Observable
import mvp.ljb.kt.model.BaseModel
import net.ljb.kt.client.HttpFactory

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/07/08
 * @Description input description
 **/
class EmptyModel : BaseModel(), EmptyContract.IModel {

    override fun getToday(page:Int): Observable<Category> {
        return HttpFactory.getProtocol(IUserHttpProtocol::class.java)
            .getToday()
    }
}

