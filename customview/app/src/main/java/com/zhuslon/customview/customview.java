package com.zhuslon.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class customview extends View {


    private int mColor;

    //画笔变量
    private Paint mpaint1;

    //三个构造函数
    public customview(Context context) {
        this(context, null);
    }

    public customview(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public customview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //加载自定义属性customview_color
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Customview);

        //解析集合属性customview_color,并设置默认颜色
        mColor = array.getColor(R.styleable.Customview_customview_color, Color.BLUE);

        //解析后释放资源

        array.recycle();

        init();

    }

    //重写onMeasure()


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取测量规则的模式和大小
        int widthMode =MeasureSpec.getMode(widthMeasureSpec);
        int widthSize= MeasureSpec.getSize(widthMeasureSpec);

        int heightMode =MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);

        //设置默认的宽、高
        int mWidth=300;
        int mHeight=300;
        if(widthMode==MeasureSpec.AT_MOST&&heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(mWidth,mHeight);
        }else if(widthMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(mWidth,heightSize);
        }else if(heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize,mHeight);
        }

    }

    //重写onDraw()
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int paddingleft = getPaddingLeft();
        final int paddingright = getPaddingRight();
        final int paddingtop = getPaddingTop();
        final int paddingbuttom = getPaddingBottom();

        //获取控件的高度和宽度
        int width =getWidth()-paddingleft-paddingright;
        int height =getHeight()-paddingtop-paddingbuttom;

        //设置圆的半径

        int r=Math.min(width,height)/2;

        //圆心，控件中央
        canvas.drawCircle(paddingleft+width/2,paddingtop+height/2,r,mpaint1);


    }

    private void init() {
        //创建画笔
        mpaint1 = new Paint();
        //设置画笔颜色
        mpaint1.setColor(mColor);
        //设置画笔宽度
        mpaint1.setStrokeWidth(5f);
        //设置画笔模式为填充
        mpaint1.setStyle(Paint.Style.FILL);

    }


}
