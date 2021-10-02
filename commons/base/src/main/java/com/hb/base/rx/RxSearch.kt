package com.hb.base.rx

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by buihai on 1/12/18.
 */
object RxSearch {
  fun fromView(view: SearchView): Observable<String> {
    val subject = PublishSubject.create<String>()
    view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String): Boolean {
//                subject.onComplete();
        return true
      }

      override fun onQueryTextChange(newText: String): Boolean {
        subject.onNext(newText)
        return true
      }
    })
    return subject
  }

  private var watcher: TextWatcher? = null
  fun fromView(view: EditText): Observable<String> {
    val subject = PublishSubject.create<String>()
    if (watcher == null) {
      watcher = MyTextWatcher(subject)
      view.addTextChangedListener(watcher)
    }
    return subject
  }

  private class MyTextWatcher internal constructor(val subject: PublishSubject<String>) :
    TextWatcher {
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
      subject.onNext(s.toString())
    }

    override fun afterTextChanged(s: Editable) {}
  }
}
