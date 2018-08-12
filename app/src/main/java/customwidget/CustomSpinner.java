package customwidget;

/**
 * Created by smoda on 6/22/2017.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parsa.marketer.R;

public class CustomSpinner extends RelativeLayout {

    TextView tvTitle;
    String hintStr;

    public CustomSpinner(Context context) {
        super(context);
    }

    public CustomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        initData(context, attrs);
    }

    public CustomSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initData(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomSpinner);
        hintStr = typedArray.getString(R.styleable.CustomSpinner_hint);


        View rootView = LayoutInflater.from(context).inflate(R.layout.custom_spinner, this);
        tvTitle = rootView.findViewById(R.id.tv_spinner_title);

        tvTitle.setText(hintStr);

    }

    public String getSelectedItem() {
        return tvTitle.getText() == null ? ""
                : (hintStr.equals(tvTitle.getText().toString()) ? "" : tvTitle.getText().toString());
    }

    public String getSpinnerHint() {
        return hintStr;
    }

    public void setSelectedItem(String title){
        tvTitle.setText(title);
        tvTitle.setTextColor(ContextCompat.getColor(getContext(),R.color.text));
    }
}