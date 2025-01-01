package com.pickstars.initializepopup.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import com.pickstars.initializepopup.R;


public class RadiusCardView extends CardView {
    private float crRadiu;  // 均匀的圆角半径
    private float tlRadiu; // 左上角半径
    private float trRadiu; // 右上角半径
    private float brRadiu; // 右下角半径
    private float blRadiu; // 左下角半径
    private Paint backgroundPaint;
    private Paint strokePaint;
    private float strokeWidth;

    public RadiusCardView(Context context) {
        this(context, null);
    }

    public RadiusCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadiusCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setRadius(0); // Disable MaterialCardView's default corner radius

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RadiusCardView);

        crRadiu = array.getDimension(R.styleable.RadiusCardView_rcv_cornerRadius, 0);
        tlRadiu = array.getDimension(R.styleable.RadiusCardView_rcv_topLeftRadius, 0);
        trRadiu = array.getDimension(R.styleable.RadiusCardView_rcv_topRightRadius, 0);
        brRadiu = array.getDimension(R.styleable.RadiusCardView_rcv_bottomRightRadius, 0);
        blRadiu = array.getDimension(R.styleable.RadiusCardView_rcv_bottomLeftRadius, 0);

        // If crRadiu is specified, override individual corner radii
        if (crRadiu > 0) {
            tlRadiu = trRadiu = brRadiu = blRadiu = crRadiu;
        }

        int backgroundColor = array.getColor(R.styleable.RadiusCardView_rcv_cardBackgroundColor, Color.WHITE);
        strokeWidth = array.getDimension(R.styleable.RadiusCardView_rcv_strokeWidth, 0);
        int strokeColor = array.getColor(R.styleable.RadiusCardView_rcv_strokeColor, Color.TRANSPARENT);

        backgroundPaint = new Paint();
        backgroundPaint.setColor(backgroundColor);
        backgroundPaint.setStyle(Paint.Style.FILL);

        strokePaint = new Paint();
        strokePaint.setColor(strokeColor);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(strokeWidth);
        strokePaint.setAntiAlias(true);

        array.recycle();

        // Remove MaterialCardView's default background and stroke
        setBackground(new ColorDrawable());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        RectF rectF = getRectF();

        // Use uniform radii if crRadiu is greater than 0
        float[] radii = crRadiu > 0
                ? new float[]{crRadiu, crRadiu, crRadiu, crRadiu, crRadiu, crRadiu, crRadiu, crRadiu}
                : new float[]{tlRadiu, tlRadiu, trRadiu, trRadiu, brRadiu, brRadiu, blRadiu, blRadiu};

        path.addRoundRect(rectF, radii, Path.Direction.CW);

        // Draw background
        canvas.drawPath(path, backgroundPaint);

        // Adjust and draw the stroke if strokeWidth is greater than 0
        if (strokeWidth > 0) {
            Path strokePath = new Path();
            RectF strokeRect = new RectF(
                    rectF.left + strokeWidth / 2,
                    rectF.top + strokeWidth / 2,
                    rectF.right - strokeWidth / 2,
                    rectF.bottom - strokeWidth / 2
            );
            strokePath.addRoundRect(strokeRect, radii, Path.Direction.CW);
            canvas.drawPath(strokePath, strokePaint);
        }

        // Clip the path to ensure child views respect the corner radii
        canvas.clipPath(path, Region.Op.INTERSECT);

        // Call the super method for default child drawing
        super.onDraw(canvas);
    }

    private RectF getRectF() {
        Rect rect = new Rect();
        getDrawingRect(rect);
        return new RectF(rect);
    }

    // Dynamically set the background color
    public void setCardBackgroundColor(int color) {
        backgroundPaint.setColor(color);
        invalidate(); // Request redraw
    }

    // Dynamically set the stroke width
    public void setStrokeWidth(float width) {
        strokeWidth = width;
        strokePaint.setStrokeWidth(strokeWidth);
        invalidate(); // Request redraw
    }

    // Dynamically set the stroke color
    public void setStrokeColor(int color) {
        strokePaint.setColor(color);
        invalidate(); // Request redraw
    }

    // Dynamically set the uniform corner radius
    public void setCornerRadius(float radius) {
        crRadiu = radius;
        tlRadiu = trRadiu = brRadiu = blRadiu = crRadiu;
        invalidate(); // Request redraw
    }

    // Dynamically set individual corner radii
    public void setTopLeftRadius(float radius) {
        tlRadiu = radius;
        invalidate();
    }

    public void setTopRightRadius(float radius) {
        trRadiu = radius;
        invalidate();
    }

    public void setBottomRightRadius(float radius) {
        brRadiu = radius;
        invalidate();
    }

    public void setBottomLeftRadius(float radius) {
        blRadiu = radius;
        invalidate();
    }
}

