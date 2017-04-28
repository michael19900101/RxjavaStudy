package com.aotuman.rxjava;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

public class RxJava01 {
	// 被观察者
	static Observable<String> mObservable = Observable.create(  
		    new Observable.OnSubscribe<String>() {  
		    	@Override
		        public void call(Subscriber<? super String> sub) {  
		        	System.out.println("被观察者[mObservable]:Hello, world!");
		            sub.onNext("Hello, world!");  
		            sub.onCompleted();  
		        }  
		    }  
		); 
	
	// 观察者
	static Subscriber<String> mSubscriber = new Subscriber<String>() {  
		@Override
	    public void onNext(String s) { 
			System.out.println("观察者[mSubscriber]:"+s); 
		}  

		@Override
	    public void onCompleted() { }  

		@Override
	    public void onError(Throwable e) { }  
	}; 
	
	// 观察者
	static Observer<String> mObserver = new Observer<String>() {
		@Override
	    public void onNext(String s) { 
			System.out.println("观察者[mObserver]:"+s); 
		}  
		
		@Override
		public void onCompleted() {}

		@Override
		public void onError(Throwable arg0) {}
		
	};
	
	static Action1<String> onNextAction = new Action1<String>(){
		// onNext()
		@Override
		public void call(String s) { 
			System.out.println("onNextAction:"+s); 
		}	
	};
	
	static Action1<Throwable> onErrorAction = new Action1<Throwable>() {
	    // onError()
	    @Override
	    public void call(Throwable throwable) {
	    	System.out.println("onError()"); 
	    }
	};
	
	static Action0 onCompletedAction = new Action0() {
	    // onCompleted()
	    @Override
	    public void call() {
	    	System.out.println("onCompleted()");
	    }
	};
	
	public static void test1(){
		// 被观察者订阅观察者
		mObservable.subscribe(mSubscriber);  
	}
	
	public static void test2(){
		// 被观察者订阅观察者
		mObservable.subscribe(mObserver); 
	}
	
	public static void test3(){
		// subscribe() 支持不完整定义的回调
		// 自动创建 ActionSubscriber(继承Subscriber)，并使用 onNextAction、 onErrorAction 和 onCompletedAction 
		// 分别定义 onNext()、 onError() 和 onComplete
		mObservable.subscribe(onNextAction);
		mObservable.subscribe(onNextAction, onErrorAction);
		mObservable.subscribe(onNextAction, onErrorAction, onCompletedAction);
	}
	
	public static void main(String[] args) {
		test1();
	}

}