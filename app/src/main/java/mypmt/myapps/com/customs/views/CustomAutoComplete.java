package mypmt.myapps.com.customs.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

/**
 * Created by android on 10-02-2015.
 */
public class CustomAutoComplete extends AutoCompleteTextView {
    public CustomAutoComplete(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomAutoComplete(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomAutoComplete(Context context) {
        super(context);
    }

    @Override
    protected CharSequence convertSelectionToString(Object selectedItem) {
        //Here is logic we have to put object-> String here!
        return super.convertSelectionToString(selectedItem);
    }
}
