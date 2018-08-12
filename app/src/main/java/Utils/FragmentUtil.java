package Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

public class FragmentUtil {

    public static void replaceFragment(FragmentManager manager,
                                       Fragment fragment,
                                       int containerId,
                                       String tag,
                                       String backStack) {

        Fragment fragmentByTag = manager.findFragmentByTag(tag);
        manager.beginTransaction()
                .replace(containerId, fragmentByTag != null ? fragmentByTag : fragment, tag)
                .addToBackStack(backStack)
                .commit();
    }

    public static void replaceFragment(FragmentManager manager,
                                       int containerId,
                                       Fragment fragment,
                                       String tag) {
        Fragment fragmentByTag = manager.findFragmentByTag(tag);
        manager.beginTransaction()
                .replace(containerId, fragmentByTag != null ? fragmentByTag : fragment, tag)
                .commit();
    }

    public static void replaceFragmentWithTransition(FragmentManager manager,
                                                     Fragment fragment,
                                                     int containerId,
                                                     String tag,
                                                     View sharedElement, String transitionName) {

        Fragment frag = manager.findFragmentByTag(tag);
        if (frag == null) {
            Fragment fragmentByTag = manager.findFragmentByTag(tag);
            manager.beginTransaction()
                    .addSharedElement(sharedElement, transitionName)
                    .replace(containerId, fragmentByTag != null ? fragmentByTag : fragment, tag)
                    .addToBackStack(null)
                    .commit();
        }else {
            manager.popBackStack();
        }
    }
}
