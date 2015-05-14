package co.geeksters.googleplaceautocomplete.bus;

import android.app.Application;

import com.squareup.otto.Bus;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by hero3 on 19/12/14.
 */
public class BaseApplication extends Application {
    private static Bus bus;
    private static Map<Object, Object> events=new HashMap<Object, Object>();


    private static BaseApplication INSTANCE = null;

    public static BaseApplication getInstance(){
        if (INSTANCE == null){
            INSTANCE = new BaseApplication();
        }
        return INSTANCE;
    }

    private BaseApplication() {
        bus = new Bus();
    }

    public  synchronized void register(Object object) {
        if (events.containsKey(object)) {
            bus.unregister(events.get(object));
        }
        events.put(object, object);
        bus.register(object);
    }

    public  synchronized void unregister(Object object) {
        events.remove(object);
        bus.unregister(object);
    }

    public  synchronized boolean isRegistered(Object object) {
        return events.containsKey(object);
    }

    public synchronized Set<Object> getRegistered() {
        return new HashSet<Object>(events.keySet());
    }

    public synchronized void unregisterAll() {
        for (Object o : events.keySet()) {
            bus.unregister(o);
        }
        events.clear();
    }

    public  void post(Object event) {
        bus.post(event);
    }
}