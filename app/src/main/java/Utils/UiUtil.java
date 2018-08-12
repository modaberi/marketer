package Utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.transition.ChangeBounds;
import android.support.transition.ChangeImageTransform;
import android.support.transition.ChangeTransform;
import android.support.transition.TransitionSet;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parsa.marketer.R;

import java.util.Locale;

public class UiUtil {

    public static void setStatusBarColor(Context context) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = ((Activity) context).getWindow();

            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // finally change the color
            window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        }
    }

    public static void showSnack(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }

    public static void showSnack(Context context, String message) {
        Toast.makeText(Util.getNotNullContext(context), message, Toast.LENGTH_LONG).show();

    }


    public static class DetailsTransition extends TransitionSet {
        public DetailsTransition() {
            setOrdering(ORDERING_TOGETHER);
            addTransition(new ChangeBounds()).
                    addTransition(new ChangeTransform()).
                    addTransition(new ChangeImageTransform());
        }
    }

    public static void updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration configuration = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            //noinspection deprecation
            configuration.locale = locale;
        }

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    public static void setupLoadingPage(Context context, View rootView, String message, EnumLoadingResponseType type) {
        TextView tvMessage = rootView.findViewById(R.id.tv_loading_message);
        ImageView image = rootView.findViewById(R.id.image_loading);
        Button btnAction = rootView.findViewById(R.id.btn_loading_action);

        tvMessage.setText(message);
        if (EnumLoadingResponseType.ERROR.name().equals(type.methodName)) {
            image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_error_gray));

        } else if (EnumLoadingResponseType.SUCCESS.name().equals(type.methodName)) {
            image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_check_gray));
        }


    }

    public static enum EnumLoadingResponseType {
        ERROR("ERROR", 1),
        SUCCESS("SUCCESS", 2);

        public String methodName;
        public int methodValue;

        EnumLoadingResponseType(String methodName, int methodValue) {
            this.methodName = methodName;
            this.methodValue = methodValue;
        }

    }

    public static void setClickedTextAction(final TextView textView,
                                            int activeColorId,
                                            final int defaultColorId){

        textView.setTextColor(activeColorId);
//        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);

         new Handler().postDelayed(() -> {
             textView.setTextColor(defaultColorId);
//             textView.setTypeface(textView.getTypeface(), Typeface.NORMAL);
          }, 150);
    }


}
