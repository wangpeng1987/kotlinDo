package  com.boo.ketlint.ui.model

import com.boo.ketlint.ui.contract.WelComeContract
import io.reactivex.Observable
import mvp.ljb.kt.model.BaseModel
import java.util.concurrent.TimeUnit

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/07/07
 * @Description input description
 **/
class WelComeModel : BaseModel(), WelComeContract.IModel {

    override fun delayGoHomeTask(): Observable<Long> {
        return Observable.timer(1500, TimeUnit.MILLISECONDS)
    }
}