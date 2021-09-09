package com.hb.base.rx;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.widget.SearchView;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by buihai on 1/12/18.
 */

public class RxSearch {

  public static Observable<String> fromView(SearchView view) {

    final PublishSubject<String> subject = PublishSubject.create();

    view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
//                subject.onComplete();
        return true;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        subject.onNext(newText);
        return true;
      }
    });

    return subject;
  }

  private static class MyTextWatcher implements TextWatcher {

    final PublishSubject<String> subject;

    MyTextWatcher(PublishSubject<String> subject) {
      this.subject = subject;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
      subject.onNext(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
  }

  private static TextWatcher watcher = null;


  public static Observable<String> fromView(EditText view) {

    final PublishSubject<String> subject = PublishSubject.create();

    if (watcher == null) {
      watcher = new MyTextWatcher(subject);
      view.addTextChangedListener(watcher);
    }

    return subject;
  }
}
