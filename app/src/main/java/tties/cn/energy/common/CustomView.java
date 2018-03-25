package tties.cn.energy.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by li on 2018/3/25
 * description：
 * author：guojlli
 */

public class CustomView extends View {
    //定义几个必要的变量
    private float currentValue = 0;     // 用于纪录当前的位置,取值范围[0,1]映射Path的整个长度
    private float[] pos;                // 当前点的实际位置
    private float[] tan;                // 当前点的tangent值,用于计算图片所需旋转的角度
    private Bitmap mBitmap;             // 箭头图片
    private Matrix mMatrix;             // 矩阵,用于对图片进行一些操作
    private int color;
    private Paint paint;
    private Paint mPaint;
    public CustomView(Context context) {
        super(context);
    }
}
