package cn.jhworks.rxnetproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import cn.jhworks.lib_common.event.RxBusHelper
import cn.jhworks.rxnetproject.meizi.MeiZiActivity
import cn.jhworks.rxnetproject.meizi.MeiZiEvent
import cn.jhworks.rxnetproject.module.BasicResult
import cn.jhworks.rxnetproject.module.MeiZi
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*
import org.net.rxnet.utils.RxNetLog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            //            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//
//            RxNet.doGet("/v3/user/3")
//                    .param("userId",String.valueOf(1))
//                    .header("cache","sdas")
//                    .excute(new CallBack());
            MeiZiActivity.start(this)

        }

        RxBusHelper.doOnMainThread(MeiZiEvent().javaClass,
                { res ->
                    if (res?.mMeiZiList != null)
                        RxNetLog.i("主页获取数据:%s", res.mMeiZiList)
                })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
