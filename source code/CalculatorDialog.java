package com.example.bussinessanalsis;

import java.text.NumberFormat;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalculatorDialog  extends Dialog
implements android.view.View.OnClickListener
{
	
	public CalculatorDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	private Button cBtn;
    private float cache;
 
    private Button cancelBtn;
    private boolean clearOnNext;
    private Context ctx;
    private Button decimalBtn;
    private String decimalSign;
    private Button divBtn;
    private Button equalsBtn;
    private float initialAmount;
    private int lastButtonClicked;
    private float memory;
    private Button minBtn;
    private Button minusBtn;
    private Button mrBtn;
    private Button multiBtn;
    private Button num0Btn;
    private Button num1Btn;
    private Button num2Btn;
    private Button num3Btn;
    private Button num4Btn;
    private Button num5Btn;
    private Button num6Btn;
    private Button num7Btn;
    private Button num8Btn;
    private Button num9Btn;
    private Button okBtn;
    private int operator;
    private Button plusBtn;
    private float sum;
    private EditText sumField;
    /*public CalculatorDialog(Context context, float f, OnAmountSetListener onamountsetlistener)
    {
        super(new ContextThemeWrapper(context, 0x7f060097));
        ctx = super.getContext();
        initialAmount = f;
        callback = onamountsetlistener;
        NumberFormat numberformat = NumberFormat.getNumberInstance();
        numberformat.setMinimumFractionDigits(2);
        decimalSign = numberformat.format(0L).substring(1, 2);
    }*/

    private void addToDisplay(String s)
    {
        if (clearOnNext)
        {
            sumField.setText(s);
            clearOnNext = false;
            return;
        } else
        {
            sumField.setText((new StringBuilder(String.valueOf(display()))).append(s).toString());
            return;
        }
    }

    private float calculateSum()
    {
        if (cache == 0.0F)
        {
            return 0.0F;
        }
        switch (operator)
        {
        default:
            throw new RuntimeException((new StringBuilder("Illegal calculator operation: ")).append(operator).toString());

        case 1: // '\001'
            return cache + getDisplay();

        case 2: // '\002'
            return cache - getDisplay();

        case 3: // '\003'
            return cache / getDisplay();

        case 4: // '\004'
            return cache * getDisplay();
        }
    }

    private String display()
    {
        return sumField.getText().toString();
    }

    private float getDisplay()
    {
        String s = sumField.getText().toString();
   return (Float) null;//CurrencyUtil.textToFloat(ctx, s);
    }

    private void handleOperator()
    {
        if (cache == 0.0F)
        {
            cache = getDisplay();
            return;
        } else
        {
            cache = calculateSum();
            setDisplay(cache);
            return;
        }
    }

    private boolean lastClickedButtonWasAnOperator()
    {
        switch (lastButtonClicked)
        {
        default:
            return false;

        case 2131296419: 
        case 2131296423: 
        case 2131296427: 
        case 2131296431: 
            return true;
        }
    }

    private void setDisplay(float f)
    {
       // sumField.setText(CurrencyUtil.formatAmountWithNoCurrency(ctx, f));
    }

    private void setDisplay(String s)
    {
        sumField.setText(s);
    }

    private void toggleButtonHighlighting()
    {
        plusBtn.setTypeface(Typeface.DEFAULT);
        minusBtn.setTypeface(Typeface.DEFAULT);
        divBtn.setTypeface(Typeface.DEFAULT);
        multiBtn.setTypeface(Typeface.DEFAULT);
       // operator;
      /*  JVM INSTR tableswitch 1 4: default 76
    //                   1 100
    //                   2 113
    //                   3 126
    //                   4 139;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        break;  Loop/switch isn't completed 
_L5:
        break MISSING_BLOCK_LABEL_139;
_L6:
        Button button = minBtn;
        Typeface typeface;
        if (memory == 0.0F)
        {
            typeface = Typeface.DEFAULT;
        } else
        {
            typeface = Typeface.DEFAULT_BOLD;
        }
        button.setTypeface(typeface);
        return;
_L2:
        plusBtn.setTypeface(Typeface.DEFAULT_BOLD);
          goto _L6
_L3:
        minusBtn.setTypeface(Typeface.DEFAULT_BOLD);
          goto _L6
_L4:
        divBtn.setTypeface(Typeface.DEFAULT_BOLD);
          goto _L6
        multiBtn.setTypeface(Typeface.DEFAULT_BOLD);
          goto _L6*/
    }

    public void onClick(View view)
    {
    
    }
     /*   view.getId();
     JVM INSTR lookupswitch 21: default 184
   
         goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22
_L1:
        toggleButtonHighlighting();
        lastButtonClicked = view.getId();
        return;
_L5:
        memory = getDisplay();
      //  SharedPreferencesManager.setCalculatorMin(ctx, memory);
        continue;  Loop/switch isn't completed 
_L6:
        setDisplay(memory);
        continue;  Loop/switch isn't completed 
_L4:
        setDisplay("");
        cache = 0.0F;
        continue;  Loop/switch isn't completed 
_L16:
        addToDisplay("1");
        continue;  Loop/switch isn't completed 
_L17:
        addToDisplay("2");
        continue;  Loop/switch isn't completed 
_L18:
        addToDisplay("3");
        continue;  Loop/switch isn't completed 
_L12:
        addToDisplay("4");
        continue;  Loop/switch isn't completed 
_L13:
        addToDisplay("5");
        continue;  Loop/switch isn't completed 
_L14:
        addToDisplay("6");
        continue;  Loop/switch isn't completed 
_L8:
        addToDisplay("7");
        continue;  Loop/switch isn't completed 
_L9:
        addToDisplay("8");
        continue;  Loop/switch isn't completed 
_L10:
        addToDisplay("9");
        continue;  Loop/switch isn't completed 
_L21:
        if (getDisplay() != 0.0F || sumField.getText().toString().contains(decimalSign) || sumField.getText().toString().length() <= 0)
        {
            addToDisplay("0");
        }
        continue;  Loop/switch isn't completed 
_L7:
        if (lastButtonClicked != 0x7f0900a3)
        {
            if (!lastClickedButtonWasAnOperator())
            {
                handleOperator();
            }
            clearOnNext = true;
            operator = 3;
        }
        continue;  Loop/switch isn't completed 
_L11:
        if (lastButtonClicked != 0x7f0900a7)
        {
            if (!lastClickedButtonWasAnOperator())
            {
                handleOperator();
            }
            clearOnNext = true;
            operator = 4;
        }
        continue;  Loop/switch isn't completed 
_L15:
        if (lastButtonClicked != 0x7f0900ab)
        {
            if (!lastClickedButtonWasAnOperator())
            {
                handleOperator();
            }
            clearOnNext = true;
            operator = 2;
        }
        continue;  Loop/switch isn't completed 
_L19:
        if (lastButtonClicked != 0x7f0900af)
        {
            if (!lastClickedButtonWasAnOperator())
            {
                handleOperator();
            }
            clearOnNext = true;
            operator = 1;
        }
        continue;  Loop/switch isn't completed 
_L20:
        if (clearOnNext)
        {
            setDisplay((new StringBuilder("0")).append(decimalSign).toString());
            clearOnNext = false;
        } else
        if (!display().contains(decimalSign))
        {
            if (display().length() == 0)
            {
                setDisplay((new StringBuilder("0")).append(decimalSign).toString());
            } else
            {
                addToDisplay(decimalSign);
            }
        }
        continue;  Loop/switch isn't completed 
_L22:
        if (lastButtonClicked != 0x7f0900b2)
        {
            if (cache == 0.0F || lastClickedButtonWasAnOperator())
            {
                sum = getDisplay();
            } else
            {
                sum = calculateSum();
            }
            cache = 0.0F;
            operator = -1;
            clearOnNext = true;
            setDisplay(sum);
        }
        continue;  Loop/switch isn't completed 
_L3:
        if (cache == 0.0F || lastClickedButtonWasAnOperator())
        {
            sum = getDisplay();
        } else
        {
            sum = calculateSum();
        }
       // callback.onAmountSet(sum);
        dismiss();
        continue;  Loop/switch isn't completed 
_L2:
        dismiss();
    }
        if (true) goto _L1; else goto _L23
_L23:
    }*/

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(0x7f030024);
        setCancelable(true);
        sumField = (EditText)findViewById(0x7f09009f);
        minBtn = (Button)findViewById(0x7f0900a1);
        mrBtn = (Button)findViewById(0x7f0900a2);
        cBtn = (Button)findViewById(0x7f0900a0);
        num7Btn = (Button)findViewById(0x7f0900a4);
        num8Btn = (Button)findViewById(0x7f0900a5);
        num9Btn = (Button)findViewById(0x7f0900a6);
        divBtn = (Button)findViewById(0x7f0900a3);
        num4Btn = (Button)findViewById(0x7f0900a8);
        num5Btn = (Button)findViewById(0x7f0900a9);
        num6Btn = (Button)findViewById(0x7f0900aa);
        multiBtn = (Button)findViewById(0x7f0900a7);
        num1Btn = (Button)findViewById(0x7f0900ac);
        num2Btn = (Button)findViewById(0x7f0900ad);
        num3Btn = (Button)findViewById(0x7f0900ae);
        minusBtn = (Button)findViewById(0x7f0900ab);
        decimalBtn = (Button)findViewById(0x7f0900b0);
        num0Btn = (Button)findViewById(0x7f0900b1);
        equalsBtn = (Button)findViewById(0x7f0900b2);
        plusBtn = (Button)findViewById(0x7f0900af);
        okBtn = (Button)findViewById(0x7f090074);
        cancelBtn = (Button)findViewById(0x7f090061);
        minBtn.setOnClickListener(this);
        mrBtn.setOnClickListener(this);
        cBtn.setOnClickListener(this);
        num7Btn.setOnClickListener(this);
        num8Btn.setOnClickListener(this);
        num9Btn.setOnClickListener(this);
        divBtn.setOnClickListener(this);
        num4Btn.setOnClickListener(this);
        num5Btn.setOnClickListener(this);
        num6Btn.setOnClickListener(this);
        multiBtn.setOnClickListener(this);
        num1Btn.setOnClickListener(this);
        num2Btn.setOnClickListener(this);
        num3Btn.setOnClickListener(this);
        minusBtn.setOnClickListener(this);
        decimalBtn.setOnClickListener(this);
        num0Btn.setOnClickListener(this);
        equalsBtn.setOnClickListener(this);
        plusBtn.setOnClickListener(this);
        okBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        sumField.setHint("0");
        if (initialAmount > 0.0F)
        {
            setDisplay(initialAmount);
            clearOnNext = true;
        }
        decimalBtn.setText(decimalSign);
     //   memory = SharedPreferencesManager.getCalculatorMin(ctx);
        toggleButtonHighlighting();
        findViewById(0x7f0900b3).requestFocus();
    }


}
