package  com.boo.ketlint.ui.model

import com.boo.ketlint.net2.domain.SearchList
import com.boo.ketlint.net2.http.IUserHttpProtocol
import com.boo.ketlint.ui.contract.SearchContract
import io.reactivex.Observable
import mvp.ljb.kt.model.BaseModel
import net.ljb.kt.client.HttpFactory

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/07/08
 * @Description input description
 **/
class SearchModel : BaseModel(), SearchContract.IModel {

    override fun getAllSeearch(page:Int): Observable<SearchList> {
        return HttpFactory.getProtocol(IUserHttpProtocol::class.java)
            .getAllSeearch(page)
    }
}

