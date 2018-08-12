package Utils;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;

import com.parsa.marketer.R;

import java.util.List;

public class ValidationUtil {

    public static boolean isValidEmail(Context context, TextInputEditText emailEditText) {

        if (emailEditText.getText().toString().length() == 0) return true;
        else if (emailEditText.getText() != null && (emailEditText.getText().toString().trim().matches("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"))) {
            return true;
        } else {
            setError(context, emailEditText, context.getString(R.string.error_regex_email));
            return false;
        }

    }

    public static boolean isValidSSN(Context context, TextInputEditText ssnEditText) {
        if (!notNullOrEmpty(ssnEditText)) {
            setError(context, ssnEditText, context.getString(R.string.error_null_ssn));

            return false;
        } else if ((ssnEditText.getText().toString().trim().length() == 10)) {
            return true;
        } else {
            setError(context, ssnEditText, context.getString(R.string.error_regex_ssn));
            return false;
        }
    }

    public static boolean isValidString(Context context, TextInputEditText usernameEditText) {

        if (!notNullOrEmpty(usernameEditText)) {
            setError(context, usernameEditText, context.getString(R.string.error_null_username));
            return false;
        } else if ((usernameEditText.getText().toString().matches("^([آ-ی]|[A-Z]|[a-z]| ){1,50}$"))) {
            return true;
        } else {
            setError(context, usernameEditText, context.getString(R.string.error_regex_username));
            return false;
        }


    }

    public static boolean isValidPassword(Context context, TextInputEditText passwordEditText) {
        if (!notNullOrEmpty(passwordEditText)) {
            setError(context, passwordEditText, context.getString(R.string.error_null_password));
            return false;
        } else
            return true;
    }

    public static boolean isValidPhone(Context context, TextInputEditText phoneEditText) {


        if (!notNullOrEmpty(phoneEditText)) {
            setError(context, phoneEditText, context.getString(R.string.error_null_phone));
            return false;
        } else if (phoneEditText.getText().toString().trim().length() < 11) {

            setError(context, phoneEditText, context.getString(R.string.error_regex_phone));
            phoneEditText.setError("تلفن همراه معتبر نیست.");
            phoneEditText.requestFocus();
            return false;
        } else if (!phoneEditText.getText().toString().trim().matches("^([\u06F0]|[\u0660]|[0])+([\u06F9]|[\u0669]|[9])+(([\u06F0-\u06F9]|[\u0660-\u0669]|[0-9])){9}$")) {
            setError(context, phoneEditText, context.getString(R.string.error_regex_phone));
            return false;
        } else {
            return true;
        }

    }

    private static void setError(Context context, TextInputEditText editText, String message) {

        editText.requestFocus();
        editText.setError(message,
                ContextCompat.getDrawable(context, R.drawable.ic_error_outline_gray));
    }

    public static boolean notNullOrEmpty(TextInputEditText editText) {
        return editText.getText() != null && editText.getText().toString().trim().length() != 0;

    }

    public static boolean notNullOrEmpty(String str) {
        return str != null && !"".equals(str);
    }

    public static boolean notNullOrEmpty(List<String> stringList) {
        return stringList == null || stringList.size() == 0;
    }


}
