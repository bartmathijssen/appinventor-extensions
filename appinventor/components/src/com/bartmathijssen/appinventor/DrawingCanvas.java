/**  ~~~~~
 * Created with the AppyBuilder Code Editor.
 * This is a template for basic Extension.
 * Modify this template to customize your extension.
 *
 * **** NOTE: DO NOT use a package name. 
 * **** The package name will be created for you automatically.
 * **** Adding a package name will cause a compile error
 */
import android.content.Context;
import android.app.Activity;
import android.util.Log;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.runtime.util.*;
import com.google.appinventor.components.common.*;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.BitmapShader;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.BlurMaskFilter;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import gnu.math.IntNum;

import java.io.InputStream;
import android.content.res.AssetManager;

import java.util.Arrays;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import android.util.DisplayMetrics;
import android.graphics.Point;
import java.util.Queue;
import java.util.LinkedList;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.LayerDrawable;

import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
////import android.view.GestureDetector.SimpleOnGestureListener;

import java.util.Random;

import 	java.util.ArrayList;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import android.os.AsyncTask;
import java.lang.Runnable;
import android.os.FileObserver;
import android.os.Environment;

import android.widget.Toast;

import java.lang.NumberFormatException;
import java.lang.IllegalArgumentException;
import java.lang.NullPointerException;

@DesignerComponent(version = 1,  description = "This Extension was created with the AppyBuilder Code Editor.<br>" + 
                   "Create your own here:<br><a href='https://editor.appybuilder.com' target='_blank'>https://editor.appybuilder.com</a><br>",
        category = ComponentCategory.EXTENSION,
        nonVisible = true,   iconName = "http://appyBuilder.com/extensions/icons/extension.png")
@SimpleObject(external = true)
@UsesPermissions(permissionNames = "android.permission.WRITE_EXTERNAL_STORAGE, android.permission.READ_EXTERNAL_STORAGE")
public class DrawingCanvas extends AndroidNonvisibleComponent {
    private ComponentContainer container;
    private Context context;
    private Activity activity;
  
    public Paint paint;
    public Path linePath;
    public Bitmap b;
    public Canvas c;
    public SimpleDrawingView canvas2;
    private int[] colors;
    public PolyUtil polyUtil;
    public int paintColor;
  
    private ScaleGestureDetector mScaleDetector;
    ////private GestureDetector.SimpleOnGestureListener mGestureListener;
    private float mScaleFactor = 1.f;
    private Rect clip;
    private float px;
    private float py;
  
    protected float cx = 0;
    protected float cy = 0;
    protected float cr = 0;
    protected boolean cfill = false;
    protected boolean circle = false;
  
    protected float recth = 0;
    protected float rectw = 0;
    protected boolean oval = false;
    protected boolean rectangle = false;
  
    protected float lx = 0;
    protected float ly = 0;
    protected boolean line = false;
    protected boolean draw = false;
  
    protected String dtext = "";
    protected boolean text = false;
    protected float textAngle;
  
    protected float nx = 0;
    protected float ny = 0;
  
    protected boolean zoom;
    private float maxZoom;
    private boolean antiAlias;
    private boolean rounded;
    private boolean texture;
    private boolean mirror;
    private String TextureImagePath = "";
  
    //private int textAlignment;
    private static final int DEFAULT_TEXTALIGNMENT = Component.ALIGNMENT_CENTER;
    private String typefaceFileName;
    private boolean bold=false;
    private boolean italic=false;
    private Typeface fontTypeface;
    private int fontType;
  
    private static final String LOG_TAG = "DrawingCanvas";
  
    public int backgroundColor;
    public BitmapDrawable backgroundDrawable;
  
    protected ArrayList<Bitmap> bitmapArray;
    protected int bitmapCount;
    protected int bitmapPosition;
    /**
     * @param container container, component will be placed in
     */
    public DrawingCanvas(ComponentContainer container) {
        super(container.$form());
        this.container = container;
        context = (Context) container.$context();
        activity = (Activity) context;
      
        canvas2 = new SimpleDrawingView(context);
        linePath = new Path();
        setupPaint();
        PaintColor(Color.BLACK);
        AntiAlias(true);
        Rounded(true);
        Texture(false);
        MaxZoom(500);
        BackgroundColor(Color.WHITE);
        FontSize(Component.FONT_DEFAULT_SIZE);
      
        bitmapArray = new ArrayList<Bitmap>();
        bitmapCount = 0;
        bitmapPosition = 0;
    }
  
  @SimpleFunction(description="Draw Heart")
  public void DrawHeart(int centerX, int centerY, int radius, boolean fill){
    int correctedX = centerX * (int)Density();
    int correctedY = centerY * (int)Density();
    int correctedR = radius * (int)Density();
    Paint p = new Paint(paint);
    p.setStyle(fill ? Paint.Style.FILL : Paint.Style.STROKE);
    
    Path path = new Path();
    
                float width = correctedR;
                float height = correctedR;
				this.c.save();
                this.c.translate(correctedX - (width / 2), correctedY - (height / 2));
                // Starting point
                path.moveTo(width / 2, height / 5); 

                // Upper left path
                path.cubicTo(5 * width / 14, 0,
                        0, height / 15,
                        width / 28, 2 * height / 5);

                // Lower left path
                path.cubicTo(width / 14, 2 * height / 3,
                        3 * width / 7, 5 * height / 6,
                        width / 2, height);

                // Lower right path
                path.cubicTo(4 * width / 7, 5 * height / 6,
                        13 * width / 14, 2 * height / 3,
                        27 * width / 28, 2 * height / 5);

                // Upper right path
                path.cubicTo(width, height / 15,
                        9 * width / 14, 0,
                        width / 2, height / 5);

        path.close();
        this.c.drawPath(path, p);
        this.c.restore();
  }
  
  @SimpleFunction(description="Draw Star")
  public void DrawStar(int centerX, int centerY, int radius, boolean fill){
    int correctedX = centerX * (int)Density();
    int correctedY = centerY * (int)Density();
    int correctedR = radius * (int)Density();
    Paint p = new Paint(paint);
    p.setStyle(fill ? Paint.Style.FILL : Paint.Style.STROKE);
    
    Path path = new Path();
    
    double alpha = (2 * Math.PI) / 10;

  for(int i = 11; i != 0; i--)
  {
    double r = correctedR*(i % 2 + 1)/2;
    double omega = alpha * i;
    if (i == 11) {
      path.moveTo((float)((r * Math.sin(omega)) + correctedX), (float)((r * Math.cos(omega)) + correctedY));
      }
    
    path.lineTo((float)((r * Math.sin(omega)) + correctedX), (float)((r * Math.cos(omega)) + correctedY));
  }

        path.close();
        this.c.drawPath(path, p);
  }

  
  /**
   * Draws a shape on the canvas.
   * pointList should be a list contains sub-lists with two number which represents a coordinate.
   * The first point and last point does not need to be the same. e.g. ((x1 y1) (x2 y2) (x3 y3))
   * When fill is true, the shape will be filled.
   *
   * @param pointList  A list of points, should contains sub-lists with two number which represents a coordinate.
   *                   The first point and last point does not need to be the same. e.g. ((x1 y1) (x2 y2) (x3 y3))
   * @param fill  true for filled shape; false for shape outline
   */
  @SimpleFunction(description = 
      "Draws a shape on the canvas. " +
      "pointList should be a list contains sub-lists with two number which represents a coordinate. " +
      "The first point and last point does not need to be the same. e.g. ((x1 y1) (x2 y2) (x3 y3)) " +
      "When fill is true, the shape will be filled.")
  public void DrawShape(YailList pointList, boolean fill) {
    Path path;
    try {
      path = parsePath(parsePointList(pointList));
    } catch (IllegalArgumentException e) {
      //$form().dispatchErrorOccurredEvent(this, "DrawShape", 1004);
      return;
    }
    path.close();
    Paint p = new Paint(paint);
    p.setStyle(fill ? Paint.Style.FILL : Paint.Style.STROKE);
    c.drawPath(path, p);
    canvas2.invalidate();
  }

  private Path parsePath(float[][] points) throws IllegalArgumentException {
    if (points == null || points.length == 0) {
      throw new IllegalArgumentException();
    }
    float scalingFactor = Density();

    Path path = new Path();
    path.moveTo(points[0][0] * scalingFactor, points[0][1] * scalingFactor);
    for (int i = 1; i < points.length; i++) {
      path.lineTo(points[i][0] * scalingFactor, points[i][1] * scalingFactor);
    }

    return path;
  }

  private float[][] parsePointList(YailList pointList) throws IllegalArgumentException {
    if (pointList == null || pointList.size() == 0) {
      throw new IllegalArgumentException();
    }
    float[][] points = new float[pointList.size()][2];
    int index = 0;
    YailList pointYailList;
    for (Object pointObject : pointList.toArray()) {
      if (pointObject instanceof YailList) {
        pointYailList = (YailList) pointObject;
        if (pointYailList.size() == 2) {
          try {
            points[index][0] = Float.parseFloat(pointYailList.getString(0));
            points[index][1] = Float.parseFloat(pointYailList.getString(1));
            index++;
          } catch (NullPointerException e) {
            throw new IllegalArgumentException(e.fillInStackTrace());
          } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e.fillInStackTrace());
          }
        } else {
          throw new IllegalArgumentException("length of item YailList("+ index +") is not 2");
        }
      } else {
        throw new IllegalArgumentException("item("+ index +") in YailList is not a YailList");
      }
    }
    return points;
  }
  
  @SimpleFunction(description="Create Canvas")
  public void CreateCanvas(AndroidViewComponent arrangement) {
    ViewGroup vg = (ViewGroup)arrangement.getView();
    canvas2 = new SimpleDrawingView(context);
    
    vg.addView(canvas2);
  }
  
  @SimpleFunction(description="GetPixelColor")
  public int GetPixelColor(int x, int y) {
    float correctedX = x * Density();
    float correctedY = y * Density();
    int tColor = b.getPixel((int)correctedX, (int)correctedY);
    return tColor;
  }
  
  /* No longer needed
  @SimpleFunction(description="FloodFillTexture")
  public void FloodFillTexture(int x, int y, int tolerance) {//, Object replacementColor
    float correctedX = x * Density();
    float correctedY = y * Density();
    final Point p1 = new Point();
    p1.x=(int) correctedX; //x co-ordinate where the user touches on the screen
    p1.y=(int) correctedY; //y co-ordinate where the user touches on the screen  
    int tColor = b.getPixel((int)correctedX, (int)correctedY);
    Paint p = new Paint(paint);
    p.setAlpha(0x00);
    p.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.CLEAR));
    int rColor = p.getColor();//getColorInt(replacementColor);
    QueueLinearFloodFiller filler = new QueueLinearFloodFiller(b, tColor, rColor);
        filler.setTolerance(tolerance);
        filler.floodFill(p1.x, p1.y);
    //canvas2.invalidate();
    
    Bitmap b2 = Bitmap.createBitmap(b.getWidth(), b.getHeight(), Bitmap.Config.ARGB_8888);
    //layerDrawable.setBounds(0, 0, b.getWidth(), b.getHeight());
    
    Paint p2 = new Paint(paint);
    p.setStyle(Paint.Style.FILL);
    if (mirror) {
        Bitmap bitmap = getBitmapFromAsset(context, TextureImagePath);
        Shader shader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        p2.setShader(shader);
      } else {
        Bitmap bitmap = getBitmapFromAsset(context, TextureImagePath);
        Shader shader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        p2.setShader(shader);
      }
    
    Canvas canvas = new Canvas(b2);
    RectF rect = new RectF(0, 0, b.getWidth(), b.getHeight());
    canvas.drawRect(rect,p2);
    
    Drawable bottomLayer = new BitmapDrawable(context.getResources(), b2);
    Drawable topLayer = new BitmapDrawable(context.getResources(), b);
    
    Drawable[] layers = new Drawable[]{bottomLayer, topLayer};
    LayerDrawable layerDrawable = new LayerDrawable(layers);
    
    Bitmap b3 = Bitmap.createBitmap(b.getWidth(), b.getHeight(), Bitmap.Config.ARGB_8888);
    layerDrawable.setBounds(0, 0, b.getWidth(), b.getHeight());
    layerDrawable.draw(new Canvas(b3));
    
    c.drawBitmap(b3, 0, 0, null);
  }
  */
  
  @SimpleFunction(description="FloodFill")
  public void FloodFill(int x, int y, int tolerance) {//, Object replacementColor
    float correctedX = x * Density();
    float correctedY = y * Density();
    final Point p1 = new Point();
    p1.x=(int) correctedX; //x co-ordinate where the user touches on the screen
    p1.y=(int) correctedY; //y co-ordinate where the user touches on the screen  
    int tColor = b.getPixel((int)correctedX, (int)correctedY);
    if (texture){
    Paint p = new Paint(paint);
    p.setAlpha(0x00);
    p.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.CLEAR));
    int rColor = p.getColor();
    QueueLinearFloodFiller filler = new QueueLinearFloodFiller(b, tColor, rColor);
        filler.setTolerance(tolerance);
        filler.floodFill(p1.x, p1.y);
    
    Bitmap b2 = Bitmap.createBitmap(b.getWidth(), b.getHeight(), Bitmap.Config.ARGB_8888);
    
    Canvas canvas = new Canvas(b2);
    RectF rect = new RectF(0, 0, b.getWidth(), b.getHeight());
    canvas.drawRect(rect, paint);
    
    Drawable bottomLayer = new BitmapDrawable(context.getResources(), b2);
    Drawable topLayer = new BitmapDrawable(context.getResources(), b);
    
    Drawable[] layers = new Drawable[]{bottomLayer, topLayer};
    LayerDrawable layerDrawable = new LayerDrawable(layers);
    
    Bitmap b3 = Bitmap.createBitmap(b.getWidth(), b.getHeight(), Bitmap.Config.ARGB_8888);
    layerDrawable.setBounds(0, 0, b.getWidth(), b.getHeight());
    layerDrawable.draw(new Canvas(b3));
    
    c.drawBitmap(b3, 0, 0, null);
    } else {
      int rColor = paint.getColor();
      QueueLinearFloodFiller filler = new QueueLinearFloodFiller(b, tColor, rColor);
        filler.setTolerance(tolerance);
        filler.floodFill(p1.x, p1.y);
    }
    canvas2.invalidate();
  }
  
  @SimpleFunction(description="ColoringBookFill")
  public void ColoringBookFill(int x, int y, Object ignoreColor, int ignoreTolerance, int fillTolerance) {//, Object replacementColor
    float correctedX = x * Density();
    float correctedY = y * Density();
    final Point p1 = new Point();
    p1.x=(int) correctedX; //x co-ordinate where the user touches on the screen
    p1.y=(int) correctedY; //y co-ordinate where the user touches on the screen  
    int tColor = b.getPixel((int)correctedX, (int)correctedY);
    int rColor = paint.getColor();//getColorInt(replacementColor);
    int iColor = getColorInt(ignoreColor);
    boolean allow = allowFill(iColor, tColor, ignoreTolerance);
    if (allow){
      QueueLinearFloodFiller filler = new QueueLinearFloodFiller(b, tColor, rColor);
        filler.setTolerance(fillTolerance);
        filler.floodFill(p1.x, p1.y);
      }
    canvas2.invalidate();
  }
  
  @SimpleFunction(description="Draw Image from file")
  public void DrawImage(int x, int y, String imageFile) {
    float correctedX = x * Density();
    float correctedY = y * Density();
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
    Bitmap bitmap = BitmapFactory.decodeFile(imageFile, options);
    
    c.drawBitmap(bitmap, correctedX, correctedY, null);
    canvas2.invalidate();
  }
  
  @SimpleFunction(description="Draw Scaled To Fit Image from file")
  public void DrawScaledToFitImage(String imageFile) {
    
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
    
    Bitmap bitmap = null;
    
      if (!imageFile.contains("//")) {
          bitmap = getBitmapFromAsset(context, imageFile);
      } else if (imageFile.startsWith("file:///android_asset/")) {
          String[] image = imageFile.split("file:///android_asset/");
          bitmap = getBitmapFromAsset(context, image[1]);
      } else {
        File file = new File(imageFile);
        bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
      }
    
    //Bitmap bitmap = BitmapFactory.decodeFile(imageFile, options);
    
    int originalBitmapWidth = b.getWidth();
    int originalBitmapHeight = b.getHeight();
    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, originalBitmapWidth, originalBitmapHeight, false);
    c.drawBitmap(scaledBitmap, 0, 0, null);
    canvas2.invalidate();
  }
  
  @SimpleFunction(description="Draw Scaled To Fit Image from file")
  public void DrawScaledToFitXYImage(int x1, int y1, int x2, int y2, String imageFile) {
    float correctedX1 = x1 * Density();
    float correctedY1 = y1 * Density();
    float correctedX2 = x2 * Density();
    float correctedY2 = y2 * Density();
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
    Bitmap bitmap = BitmapFactory.decodeFile(imageFile, options);
    
    int width = Math.round(correctedX2) - Math.round(correctedX1);
    int height = Math.round(correctedY2) - Math.round(correctedY1);
    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
    c.drawBitmap(scaledBitmap, correctedX1, correctedY1, null);
    canvas2.invalidate();
  }
  
  @SimpleFunction(description="CropAndSave")
  public void CropAndSave(int x1, int y1, int x2, int y2, String filePath) {
    float correctedX1 = x1 * Density();
    float correctedY1 = y1 * Density();
    float correctedX2 = x2 * Density();
    float correctedY2 = y2 * Density();
    
    int width = Math.round(correctedX2) - Math.round(correctedX1);
    int height = Math.round(correctedY2) - Math.round(correctedY1);
    Bitmap bitmap = Bitmap.createBitmap(b, Math.round(correctedX1), Math.round(correctedY1), width, height);
    
    //new SaveData().execute(filePath);
    
    
    File file = new File(filePath);

    // Create the parent directory.
    file.getParentFile().mkdirs();
    
    FileOutputStream fos = null;
    // Save Bitmap to File
try
{
    fos = new FileOutputStream(file);
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

    fos.flush();
    fos.close();
    fos = null;
    SavingCompleted(true, file.getName());
}
catch (IOException e)
{
    e.printStackTrace();
    SavingCompleted(false, file.getName());
}
finally
{
    if (fos != null)
    {
        try
        {
            fos.close();
            fos = null;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            SavingCompleted(false, file.getName());
        }
    }
}
  }
  
  @SimpleFunction(description = "ArrangementToPNG")
    public void ArrangementToPNG(AndroidViewComponent arrangement, String filePath){
    View view = (View)arrangement.getView();
    Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);                
    Canvas canvas = new Canvas(bitmap);
    view.draw(canvas);
      
    ////file
    File file = new File(filePath);

    // Create the parent directory.
    file.getParentFile().mkdirs();
    
    FileOutputStream fos = null;
    // Save Bitmap to File
try
{
    fos = new FileOutputStream(file);
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

    fos.flush();
    fos.close();
    fos = null;
    SavingCompleted(true, file.getName());
}
catch (IOException e)
{
    e.printStackTrace();
    SavingCompleted(false, file.getName());
}
finally
{
    if (fos != null)
    {
        try
        {
            fos.close();
            fos = null;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            SavingCompleted(false, file.getName());
        }
    }
}
    }
  
  @SimpleFunction(description="Save As .png")
  public void SaveAsPNG(String filePath) {
    new SaveData().execute(filePath);
    /*
    File file = new File(filePath);

    // Create the parent directory.
    file.getParentFile().mkdirs();
    
    FileOutputStream fos = null;
    
    Bitmap bitmap = Bitmap.createBitmap(canvas2.getWidth(), canvas2.getHeight(), Bitmap.Config.ARGB_8888);                
    Canvas canvas = new Canvas(bitmap);
    canvas2.draw(canvas);
    
    // Save Bitmap to File
try
{
    fos = new FileOutputStream(file);
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

    fos.flush();
    fos.close();
    fos = null;
    SavingCompleted(true, file.getName());
}
catch (IOException e)
{
    e.printStackTrace();
    SavingCompleted(false, file.getName());
}
finally
{
    if (fos != null)
    {
        try
        {
            fos.close();
            fos = null;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            SavingCompleted(false, file.getName());
        }
    }
}
    */
  }
  
  
  class SaveData extends AsyncTask<String,Void,String>{

  @Override
  protected String doInBackground(String... filePath) {
    // your background code here. Doesn't touch any UI components
    final File file = new File(filePath[0]);
    // Create the parent directory.
    file.getParentFile().mkdirs();
    
    FileOutputStream fos = null;
    
    // Save Bitmap to File
  try
  {
    fos = new FileOutputStream(file);
    b.compress(Bitmap.CompressFormat.PNG, 100, fos);

    fos.flush();
    fos.close();
    fos = null;
   activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(context, file.getName() , Toast.LENGTH_LONG).show();
                            SavingCompleted(true, file.getName());
                        }
                    });
   
  }
    catch (IOException e)
  {
    e.printStackTrace();
    SavingCompleted(false, filePath[0]);
  }
    finally
  {
    if (fos != null)
    {
        try
        {
            fos.close();
            fos = null;
        }
        catch (IOException e)
        {
            e.printStackTrace();
          String failed = "false" + "," + filePath[0];
          SavingCompleted(false, filePath[0]);
            return failed;
        }
    }
  }
  
  String successful = "true" + "," + filePath[0];
  return successful;
    
  }

  protected void onPostExecute(String result) {
     super.onPostExecute(result);
     final String[] results = result.split(",");
     //This is run on the UI thread so you can do as you wish here
     //String filepath = new File(resultss[1]).getParent() + "/";
     //Toast.makeText(context, filepath, Toast.LENGTH_LONG).show();
     if (results[0] == "true") {
     
     }
  }
    
  }
  
  @SimpleEvent(description="SavingCompleted")
  public void SavingCompleted(boolean result, String fileName)
  {
    EventDispatcher.dispatchEvent(this, "SavingCompleted", result, fileName);
  }
  
 /* 
  @SimpleFunction(description="Save Canvas Only")
  public void SaveCanvasOnly(String filePath) {
    new SaveData().execute(filePath);
    /*
    File file = new File(filePath);

    // Create the parent directory.
    file.getParentFile().mkdirs();
    
    FileOutputStream fos = null;
    
    // Save Bitmap to File
try
{
    fos = new FileOutputStream(file);
    b.compress(Bitmap.CompressFormat.PNG, 100, fos);

    fos.flush();
    fos.close();
    fos = null;
}
catch (IOException e)
{
    e.printStackTrace();
}
finally
{
    if (fos != null)
    {
        try
        {
            fos.close();
            fos = null;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

}
*/
  
    @SimpleFunction(description="Save As .jpg")
  public void SaveAsJPEG(String filePath, int compression) {
    File file = new File(filePath);

    // Create the parent directory.
    file.getParentFile().mkdirs();
    
    FileOutputStream fos = null;
    
    Bitmap bitmap = Bitmap.createBitmap(canvas2.getWidth(), canvas2.getHeight(), Bitmap.Config.ARGB_8888);                
    Canvas canvas = new Canvas(bitmap);
    canvas2.draw(canvas);
    
    // Save Bitmap to File
try
{
    fos = new FileOutputStream(file);
    bitmap.compress(Bitmap.CompressFormat.JPEG, compression, fos);

    fos.flush();
    fos.close();
    fos = null;
    SavingCompleted(true, file.getName());
}
catch (IOException e)
{
    e.printStackTrace();
    SavingCompleted(false, file.getName());
}
finally
{
    if (fos != null)
    {
        try
        {
            fos.close();
            fos = null;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            SavingCompleted(false, file.getName());
        }
    }
}
  }
  
  
  public float Density() {
      DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.density;
  }
  
  @SimpleFunction(description = "Draws the specified text relative to the specified coordinates "
      + "using the values of the FontSize.")
  public void DrawText(String text, int x, int y) {
    float fontScalingFactor = Density();
    float correctedX = x * fontScalingFactor;
    float correctedY = y * fontScalingFactor;
    c.drawText(text, correctedX, correctedY, paint);
    //canvas2.invalidate();
  }

  @SimpleFunction(description = "Draws the specified text starting at the specified coordinates "
      + "at the specified angle using the values of the FontSize.")
  public void DrawTextAtAngle(String text, int x, int y, float angle) {
    int correctedX = (int) (x * Density());
    int correctedY = (int) (y * Density());
    drawTextAtAngle(text, correctedX, correctedY, angle);
  }
  
  private void drawTextAtAngle(String text, int x, int y, float angle) {
      c.save();
      c.rotate(-angle, x, y);
      c.drawText(text, x, y, paint);
      c.restore();
      //canvas2.invalidate();
    }
  
  
  @SimpleFunction(description="Draw Circle")
  public void DrawCircle(int centerX, int centerY, float radius, boolean fill){
    float correctedX = centerX * Density();
    float correctedY = centerY * Density();
    float correctedR = radius * Density();
    Paint p = new Paint(paint);
    p.setStyle(fill ? Paint.Style.FILL : Paint.Style.STROKE);
    c.drawCircle(correctedX, correctedY, correctedR, p);
    //canvas2.invalidate();
  }
  
  //https://stackoverflow.com/a/11938713/2134199
  @SimpleFunction(description="SprayPaint")
  public void SprayPaint(int x, int y, int amount){
    float correctedX = x * Density();
    float correctedY = y * Density();
    float correctedR1 = LineWidth() / 2;
    float correctedR2 = LineWidth() / 4;
    Paint p = new Paint(paint);
    p.setStyle(Paint.Style.FILL);
    p.setStrokeWidth((int)Density());
    int dotsToDrawAtATime = Math.round(correctedR1) * amount;
    Random r = new Random();
    
    for (int i = 0; i < dotsToDrawAtATime; i++){
        
        float x2 = correctedX + (float)r.nextGaussian()*correctedR1;
        float y2 = correctedY + (float)r.nextGaussian()*correctedR1;
        float x3 = correctedX + (float)r.nextGaussian()*correctedR2;
        float y3 = correctedY + (float)r.nextGaussian()*correctedR2;
        c.drawPoint(x2, y2, p);
        c.drawPoint(x3, y3, p);
    }
    //canvas2.invalidate();
  }
  
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = true)
  public int FontTypeface() {
    return fontType;
  }

  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_TYPEFACE, defaultValue = Component.TYPEFACE_DEFAULT + "")
  @SimpleProperty(userVisible = true)
  public void FontTypeface(int typeface) {
    fontType = typeface;
    setFontTypeface();
  }

  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_ASSET, defaultValue = "")
  @SimpleProperty(userVisible=true)
  public void FontTypefaceCustom(String fontFileName)
  {
    if (fontFileName == null || fontFileName.isEmpty()) {
//      this.fontTypeface = Typeface.DEFAULT;
      return;
    }

    Typeface localTypeface1 = null;
   // try {
      if (!fontFileName.contains("//")) {
        localTypeface1 = Typeface.createFromAsset(context.getAssets(), fontFileName);
      } else if (fontFileName.startsWith("file:///android_asset/")) {
        String[] fontFile = fontFileName.split("file:///android_asset/");
        localTypeface1 = Typeface.createFromAsset(context.getAssets(), fontFile[1]); 
      } else {
        File file = new File(fontFileName);
        localTypeface1 = Typeface.createFromFile(file);
    }
    // } catch (IOException e) {
    //  Log.e(LOG_TAG, "Unable to load your font: " + fontFileName);
    //  return;
   // }

    if (localTypeface1 == null) return;

      this.fontTypeface = localTypeface1;
      this.typefaceFileName = fontFileName;


    int style = 0;
    if (bold) {
      style |= Typeface.BOLD;
    }
    if (italic) {
      style |= Typeface.ITALIC;
    }

    paint.setTypeface(Typeface.create(fontTypeface, style));

  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = true)
  public boolean FontBold() {
    return bold;
  }

  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN, defaultValue = "False")
  @SimpleProperty(userVisible = true)
  public void FontBold(boolean bold) {
    this.bold = bold;
    setFontTypeface();
  }

  @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = true)
  public boolean FontItalic() {
    return italic;
  }

  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN, defaultValue = "False")
  @SimpleProperty(userVisible = true)
  public void FontItalic(boolean italic) {
    this.italic = italic;
    setFontTypeface();
  }

  private void setFontTypeface() {

    if (fontType == Component.TYPEFACE_SANSSERIF) fontTypeface = Typeface.SANS_SERIF;
    else if (fontType == Component.TYPEFACE_SERIF) fontTypeface = Typeface.SERIF;
    else if (fontType == Component.TYPEFACE_MONOSPACE) fontTypeface = Typeface.MONOSPACE;
    else fontTypeface = Typeface.DEFAULT;

    int style = 0;
    if (bold) {
      style |= Typeface.BOLD;
    }
    if (italic) {
      style |= Typeface.ITALIC;
    }

    paint.setTypeface(Typeface.create(fontTypeface, style));
  }

  @SimpleFunction(description="Draw Vertical Gradient Circle")
  public void DrawVerticalGradientCircle(int centerX, int centerY, float radius, boolean fill, YailList colorList) {
    String[] strArray = colorList.toStringArray();
       colors = new int[strArray.length];
       for (int i = 0; i < strArray.length; i++) {
           colors[i] = getColorIntNew(IntNum.valueOf(strArray[i]));
       }
    float correctedX = centerX * Density();
    float correctedY = centerY * Density();
    float correctedR = radius * Density();
  Paint p = new Paint(paint);
    p.setStyle(fill ? Paint.Style.FILL : Paint.Style.STROKE);
    p.setShader(new LinearGradient(correctedX - correctedR, correctedY - correctedR, correctedX - correctedR, correctedY + correctedR, colors, null, Shader.TileMode.CLAMP));
    c.drawCircle(correctedX, correctedY, correctedR, p);
    canvas2.invalidate();
  }
  
  @SimpleFunction(description="Draw Horizontal Gradient Circle")
  public void DrawHorizontalGradientCircle(int centerX, int centerY, float radius, boolean fill, YailList colorList) {
    String[] strArray = colorList.toStringArray();
       colors = new int[strArray.length];
       for (int i = 0; i < strArray.length; i++) {
           colors[i] = getColorIntNew(IntNum.valueOf(strArray[i]));
       }
    float correctedX = centerX * Density();
    float correctedY = centerY * Density();
    float correctedR = radius * Density();
  Paint p = new Paint(paint);
    p.setStyle(fill ? Paint.Style.FILL : Paint.Style.STROKE);
    p.setShader(new LinearGradient(correctedX - correctedR, correctedY - correctedR, correctedX + correctedR, correctedY - correctedR, colors, null, Shader.TileMode.CLAMP));
    c.drawCircle(correctedX, correctedY, correctedR, p);
    canvas2.invalidate();
  }
  
  ////
  
   // good tutorial: https://developer.mozilla.org/en-US/docs/Web/API/Canvas_API/Tutorial/Drawing_shapes

  @SimpleFunction(description = "Creates a polygon with with specified number of sides from a radius")
  public void DrawPolygon(float centerX, float centerY, int numSides, float polyRadius, float cornerRadius, float rotation, boolean fill) {
    if (polyUtil == null)
      polyUtil = new PolyUtil();

    // clear the prior shape. Is this needed? If not mentioned, then in blocks, we'll first need to do Canvas.Clear, then call this method
    ////view.clearDrawingLayer();

    Paint p = new Paint(paint);
    p.setStyle(fill ? Paint.Style.FILL : Paint.Style.STROKE);

    polyUtil.drawPolygon(
            c,
            numSides,
            centerX * Density(),
            centerY * Density(),
            polyRadius * Density(),
            cornerRadius * Density(),
            rotation,
            p);

    //canvas2.invalidate();
  }


  public void _DrawPloygon(int x, int y, float drawAngle, float skewX, float skewY, float radiusInner, float radiusOuter, int points) {
    double section = 2.0 * Math.PI / points;
    Path path = new Path();
    path.moveTo((float) (x + radiusInner * Math.cos(0)),
            (float) (y + radiusInner * Math.sin(0)));
    path.lineTo((float) (x + radiusOuter * Math.cos(0 + section / 2.0)),
            (float) (y + radiusOuter * Math.sin(0 + section / 2.0)));

    for (int i = 1; i < points; i++) {
      path.lineTo((float) (x + radiusInner * Math.cos(section * i)),
              (float) (y + radiusInner * Math.sin(section * i)));
      path.lineTo(
              (float) (x + radiusOuter * Math.cos(section * i + section / 2.0)),
              (float) (y + radiusOuter * Math.sin(section * i + section / 2.0)));
    }
    path.close();

    DrawPrepare(path, drawAngle, skewX, skewY );
    c.drawPath(path, paint);
    //canvas2.invalidate();
  }

  private void DrawPrepare(Path path, float drawAngle, float kx, float ky) {
    Matrix matrix = new Matrix();
    matrix.postRotate(drawAngle);
    matrix.setSkew(kx, ky);
    path.transform(matrix);
    // view.canvas.save();
    // view.canvas.rotate(-drawAngle, x, y);
  }
  ////
  
  @SimpleFunction(description="Draw Line")
  public void DrawLine(int fromX, int fromY, int toX, int toY){
    float correctedX1 = fromX * Density();
    float correctedY1 = fromY * Density();
    float correctedX2 = toX * Density();
    float correctedY2 = toY * Density();
    
      linePath = new Path();
        linePath.moveTo(correctedX1, correctedY1);
        linePath.lineTo(correctedX2, correctedY2);
    
    Paint p = new Paint(paint);
    p.setStyle(Paint.Style.STROKE);
    
    c.drawPath(linePath, p);
    linePath = new Path();
    //canvas2.invalidate();
  }
  
  @SimpleFunction(description="Draw Oval")
  public void DrawOval(int x, int y, int height, int width, boolean fill){
    float correctedX = x * Density();
    float correctedY = y * Density();
    float correctedHeight = (height * Density()) / 2;
    float correctedWidth = (width * Density()) / 2;
    
    RectF rect = new RectF(correctedX - correctedWidth, correctedY - correctedHeight, correctedX + correctedWidth, correctedY + correctedHeight);
    
    Paint p = new Paint(paint);
    p.setStyle(fill ? Paint.Style.FILL : Paint.Style.STROKE);
    
    c.drawOval(rect,p);
    //canvas2.invalidate();
  }
  
  @SimpleFunction(description="Draw Rectangle")
  public void DrawRectangle(int x, int y, int height, int width, boolean fill){
    float correctedX = x * Density();
    float correctedY = y * Density();
    float correctedHeight = height * Density();
    float correctedWidth = width * Density();
    
    RectF rect = new RectF(correctedX, correctedY, correctedX + correctedWidth, correctedY + correctedHeight);
    
    Paint p = new Paint(paint);
    p.setStyle(fill ? Paint.Style.FILL : Paint.Style.STROKE);
    
    c.drawRect(rect,p);
    //canvas2.invalidate();
  }
  
       @SimpleFunction(description = "Draws the specified text relative to the specified coordinates "
      + "using the values of the FontSize.")
  public void SetDrawingText(String text, float angle) {
    float correctedX = b.getWidth() / 2;
    float correctedY = b.getHeight() / 2;
    this.textAngle = angle;
    cx = correctedX;
    cy = correctedY;
    
    if (text != ""){
      dtext = text;
      this.text = true;
    } else {
      this.text = false;
    }
    canvas2.invalidate();
  }
  
  @SimpleFunction(description="Drawing Text Down")
       public void DrawingTextDown(int x, int y){
         float correctedX = x * Density();
         float correctedY = y * Density();
         
         if (!zoom && dtext != "") {
           cx = correctedX;
           cy = correctedY;
           this.text = true;
         }
      }
    
       @SimpleFunction(description="Drawing Text Moved")
       public void DrawingTextMoved(int x, int y) {
         float correctedX = x * Density();
         float correctedY = y * Density();
         
         if (!zoom) {
           cx = correctedX;
           cy = correctedY;
         }
       }
  
       @SimpleFunction(description="Drawing Text Up")
       public void DrawingTextUp(int x, int y) {
         float correctedX = x * Density();
         float correctedY = y * Density();
         
         if (!zoom) {
           cx = correctedX;
           cy = correctedY;
           c.save();
           c.rotate(-textAngle, cx, cy);
           c.drawText(dtext, cx, cy, paint);
           c.restore();
           //c.drawText(dtext, cx, cy, paint);
         }
         cx = 0;
         cy = 0;
         this.text = false;
         //canvas2.invalidate();
       }
  
       @SimpleFunction(description="Drawing Down")
       public void DrawingDown(int x, int y){
         float correctedX = x * Density();
         float correctedY = y * Density();
    
         if (!zoom) {
           linePath = new Path();
           linePath.moveTo(correctedX, correctedY);
           this.draw = true;
         }
      }
    
       @SimpleFunction(description="Drawing Moved")
       public void DrawingMoved(int x, int y) {
         float correctedX = x * Density();
         float correctedY = y * Density();
         if (!zoom) {
           linePath.lineTo(correctedX, correctedY);
         }
       }
  
       @SimpleFunction(description="Drawing Up")
       public void DrawingUp(int x, int y) {
         float correctedX = x * Density();
         float correctedY = y * Density();
         
         if (!zoom) {
           linePath.lineTo(correctedX, correctedY);
           Paint p = new Paint(paint);
           p.setStyle(Paint.Style.STROKE);
           //p.setMaskFilter(new BlurMaskFilter(LineWidth() / 2, BlurMaskFilter.Blur.INNER));//possible future implementation for blurred edges on line
           c.drawPath(linePath, p);
         }
         linePath.reset();
         this.draw = false;
         //canvas2.invalidate();
       }
  
    @SimpleFunction(description="Drawing Circle Down")
       public void DrawingCircleDown(int x, int y, boolean fill){
         float correctedX = x * Density();
         float correctedY = y * Density();
         
         cfill = fill;
         if (!zoom) {
           cx = correctedX;
           cy = correctedY;
           cr = 0;
           circle = true;
         }
      }
    
       @SimpleFunction(description="Drawing Circle Moved")
       public void DrawingCircleMoved(int x, int y) {
         float correctedX = x * Density();
         float correctedY = y * Density();
         
         if (!zoom) {
              cr = Math.max(Math.abs(cx - correctedX), Math.abs(cy - correctedY)) ;
         }
       }
  
       @SimpleFunction(description="Drawing Circle Up")
       public void DrawingCircleUp(int x, int y) {
         float correctedX = x * Density();
         float correctedY = y * Density();
         
         Paint p = new Paint(paint);
         p.setStyle(cfill ? Paint.Style.FILL : Paint.Style.STROKE);
         
         if (!zoom) {
           cr = Math.max(Math.abs(cx - correctedX), Math.abs(cy - correctedY)) ;
           c.drawCircle(cx, cy, cr, p);
         }
         cx = 0;
         cy = 0;
         cr = 0;
         circle = false;
         //canvas2.invalidate();
       }
  
  @SimpleFunction(description="Drawing Oval Down")
    public void DrawingOvalDown(int x, int y, boolean fill){
    float correctedX = x * Density();
    float correctedY = y * Density();
    
    cfill = fill;
         if (!zoom) {
           cx = correctedX;
           cy = correctedY;
           recth = 0;
           rectw = 0;
           oval = true;
         }
    //canvas2.invalidate();
  }
  
  @SimpleFunction(description="Drawing Oval Moved")
       public void DrawingOvalMoved(int x, int y) {
         float correctedX = x * Density();
         float correctedY = y * Density();
         
         if (!zoom) {
           rectw = Math.abs(cx - correctedX);
           recth = Math.abs(cy - correctedY);
         }
       }
  
       @SimpleFunction(description="Drawing Oval Up")
       public void DrawingOvalUp(int x, int y) {
         float correctedX = x * Density();
         float correctedY = y * Density();
    
         Paint p = new Paint(paint);
         p.setStyle(cfill ? Paint.Style.FILL : Paint.Style.STROKE);
         
         if (!zoom) {
           rectw = Math.abs(cx - correctedX);
           recth = Math.abs(cy - correctedY);
           RectF rect = new RectF(cx - rectw, cy - recth, cx + rectw, cy + recth);
           c.drawOval(rect,p);
         }
         cx = 0;
         cy = 0;
         recth = 0;
         rectw = 0;
         oval = false;
         //canvas2.invalidate();
       }
  
       @SimpleFunction(description="Drawing Rectangle Down")
    public void DrawingRectangleDown(int x, int y, boolean fill){
    float correctedX = x * Density();
    float correctedY = y * Density();
    
    cfill = fill;
         if (!zoom) {
           cx = correctedX;
           cy = correctedY;
           nx = correctedX;
           ny = correctedY;
           //recth = 0;
           //rectw = 0;
           rectangle = true;
         }
    //canvas2.invalidate();
  }
  
       @SimpleFunction(description="Drawing Rectangle Moved")
       public void DrawingRectangleMoved(int x, int y) {
         float correctedX = x * Density();
         float correctedY = y * Density();
         
         if (!zoom) {
           nx = correctedX;
           ny = correctedY;
           //rectw = Math.abs(cx - nx);
           //recth = Math.abs(cy - ny);
         }
       }
  
       @SimpleFunction(description="Drawing Rectangle Up")
       public void DrawingRectangleUp(int x, int y) {
         float correctedX = x * Density();
         float correctedY = y * Density();
    
         Paint p = new Paint(paint);
         p.setStyle(cfill ? Paint.Style.FILL : Paint.Style.STROKE);
         
         if (!zoom) {
           nx = correctedX;
           ny = correctedY;
           //rectw = Math.abs(cx - nx);
           //recth = Math.abs(cy - ny);
           //if(cx < nx && cy < ny){
             RectF rect = new RectF(cx, cy, nx, ny);//cx + rectw, cy + recth);
             c.drawRect(rect, p);
           //} else {
           //  RectF rect = new RectF(nx, ny, cx, cy);//cx - rectw, cy - recth, cx, cy);
          //  c.drawRect(rect, p);
           //}
         }
         cx = 0;
         cy = 0;
         nx = 0;
         ny = 0;
         rectangle = false;
         //canvas2.invalidate();
       }
  
       @SimpleFunction(description="Drawing Line Down")
    public void DrawingLineDown(int x, int y){
    float correctedX = x * Density();
    float correctedY = y * Density();
    
         if (!zoom) {
           cx = correctedX;
           cy = correctedY;
           lx = correctedX;
           ly = correctedY;
           line = true;
         }
    //canvas2.invalidate();
  }
  
       @SimpleFunction(description="Drawing Line Moved")
       public void DrawingLineMoved(int x, int y) {
         float correctedX = x * Density();
         float correctedY = y * Density();
         
         if (!zoom) {
           lx = correctedX;
           ly = correctedY;
         }
       }
  
       @SimpleFunction(description="Drawing Line Up")
       public void DrawingLineUp(int x, int y) {
         float correctedX = x * Density();
         float correctedY = y * Density();
    
         Paint p = new Paint(paint);
          p.setStyle(Paint.Style.STROKE);
         
         lx = correctedX;
         ly = correctedY;
         
         Path linPath = new Path();
          linPath.moveTo(cx, cy);
          linPath.lineTo(lx, ly);
         
         if (!zoom) {
          c.drawPath(linPath, p);
         }
         cx = 0;
         cy = 0;
         lx = 0;
         ly = 0;
         line = false;
         //canvas2.invalidate();
       }
  
  @SimpleFunction(description = "Clears anything drawn on the canvas.")
  public void Clear() {
    c.drawColor(0, Mode.CLEAR);
    c.drawColor(backgroundColor);
    canvas2.invalidate();
  }
  
    private void setupPaint() {
		// Setup paint with stroke style
      paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
	}
  
    public static int getColorInt(Object colorType){
    int colorIntValue = 0;
    IntNum number = null;
    if (colorType instanceof String){
      colorIntValue = Color.parseColor((String) colorType);
    } else { 
      number = (IntNum) colorType;
      colorIntValue = number.intValue();
    }
    return colorIntValue;
}
  
  public static int getColorIntNew(Object colorType){
    int colorIntValueNew = 0;
    IntNum number = null;
      number = (IntNum) colorType;
      colorIntValueNew = number.intValue();
    return colorIntValueNew;
}
  
  @SimpleEvent
  public void TouchDown(float x, float y) {
    EventDispatcher.dispatchEvent(this, "TouchDown", x, y);
  }

  @SimpleEvent
  public void TouchUp(float x, float y) {
    EventDispatcher.dispatchEvent(this, "TouchUp", x, y);
  }
  
    @SimpleEvent
  public void Moved(float x, float y) {
    EventDispatcher.dispatchEvent(this, "Moved", x, y);
  }
  
  @SimpleFunction(description="ZoomTo")
  public void ZoomTo(int x, int y, float scaleFactor) {
    px = x * Density();
    py = y * Density();
    mScaleFactor = scaleFactor;
    canvas2.invalidate();
  }
  
  @SimpleFunction(description="Reset Zoom")
  public void ResetZoom() {
    px = 0;
    py = 0;
    mScaleFactor = 1f;
    canvas2.invalidate();
  }
  
    public class SimpleDrawingView extends View {
	// setup initial color
	private final int paintColor = Color.BLACK;
    private Paint pBg;

      public SimpleDrawingView(Context context) {
		super(context);
		setFocusable(true);
		setFocusableInTouchMode(true);
        
     //setupPaint();
        pBg = new Paint();
        pBg.setColor(Color.WHITE);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        ////mGestureListener = new GestureDetector.SimpleOnGestureListener(context, new GestureListener());
	}
      
	@Override
	protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor, px, py);
      
        canvas.drawBitmap(b, 0, 0, pBg);
      
        if (draw && !zoom){
          Paint p = new Paint(paint);
          p.setStyle(Paint.Style.STROKE);
          canvas.drawPath(linePath, p);
        }
      
        if (text && !zoom){
          canvas.save();
          canvas.rotate(-textAngle, cx, cy);
          canvas.drawText(dtext, cx, cy, paint);
          canvas.restore();
         //canvas.drawText(dtext, cx, cy, paint);
        }
      
        if (line && !zoom){
          Path linPath = new Path();
          linPath.moveTo(cx, cy);
          Paint pl = new Paint(paint);
          pl.setStyle(Paint.Style.STROKE);
            linPath.lineTo(lx, ly);
            canvas.drawPath(linPath, pl);
        }
      
        if (circle && !zoom){
          Paint pc = new Paint(paint);
          pc.setStyle(cfill ? Paint.Style.FILL : Paint.Style.STROKE);
          canvas.drawCircle(cx, cy, cr, pc);
        }
         
       if (oval && !zoom) {
         Paint po = new Paint(paint);
         po.setStyle(cfill ? Paint.Style.FILL : Paint.Style.STROKE);
         RectF rect = new RectF(cx - rectw, cy - recth, cx + rectw, cy + recth);
         canvas.drawOval(rect, po);
       }
        
        if (rectangle && !zoom) {
         Paint po = new Paint(paint);
         po.setStyle(cfill ? Paint.Style.FILL : Paint.Style.STROKE);
         /*
         if (cx <= nx && cy <= ny) {
           RectF rect = new RectF(cx, cy, nx, ny);
           canvas.drawRect(rect, po);
         } else {
           RectF rect = new RectF(nx, ny, cx, cy);
           canvas.drawRect(rect, po);
         }
         **/
         //RectF rect = new RectF(cx, cy, cx + rectw, cy + recth);
         RectF rect = new RectF(Math.min(cx,nx), Math.min(cy,ny), Math.max(nx,cx), Math.max(ny,cy));
         canvas.drawRect(rect, po);
       }
        
        clip = canvas.getClipBounds();
      
        canvas.restore();
	}
      
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
      super.onSizeChanged(w, h, oldw, oldh);
      b = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
      c = new Canvas(b);
      c.drawColor(backgroundColor);
    }

	@Override
	public boolean onTouchEvent(MotionEvent event) {
      if (zoom) {
          mScaleDetector.onTouchEvent(event);
      }
      
        float correctedX = Math.max(0, (event.getX(0) / mScaleFactor + clip.left) / Density());
        float correctedY = Math.max(0, (event.getY(0) / mScaleFactor + clip.top) / Density());
        float corX = Math.max(0, ((event.getX(0) / mScaleFactor) + clip.left));
        float corY = Math.max(0, ((event.getY(0) / mScaleFactor) + clip.top));
        float secondX;
        float secondY;
      
        if (event.getPointerCount() == 2){
              secondX = Math.max(0, ((event.getX(1) / mScaleFactor) + clip.left));
              secondY = Math.max(0, ((event.getY(1) / mScaleFactor) + clip.top));
              px = (corX + secondX) / 2;
              py = (corY + secondY) / 2;
            }
	
		// Checks for the event that occurs
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:  
            zoom = false;
            if (!zoom) {
              cx = correctedX;
              cy = correctedY;
              TouchDown(correctedX, correctedY);
            }
			////return true;
            break;
		case MotionEvent.ACTION_MOVE:
            if (!zoom) {
              int historySize = event.getHistorySize();
          for (int i = 0; i < historySize; i++) {
            float historicalX = event.getHistoricalX(i);
            float historicalY = event.getHistoricalY(i);
            //linePath.lineTo(historicalX, historicalY);
            }
              Moved(correctedX, correctedY);
            }
			break;
        case MotionEvent.ACTION_UP:
            if (!zoom) {
              TouchUp(correctedX, correctedY);
            }
            break;
        case MotionEvent.ACTION_POINTER_DOWN:
            zoom = true;
            linePath.reset();
            break;
		default:
			return false;
		}
		// Force a view to draw again
		postInvalidate();
		return true;
	}
}
  
  private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        mScaleFactor *= detector.getScaleFactor();
        // Don't let the object get too small or too large.
        mScaleFactor = Math.max(1f, Math.min(mScaleFactor, maxZoom));
        //canvas2.invalidate();
        return true;
    }
  }
  
  
  public class QueueLinearFloodFiller {

    protected Bitmap image = null;
    protected int[] tolerance = new int[] { 0, 0, 0, 0 };
    protected int[] startColor = new int[] { 0, 0, 0, 0 };
    protected int width = 0;
    protected int height = 0;
    protected int[] pixels = null;
    protected int fillColor = 0;
    protected boolean[] pixelsChecked;
    protected Queue<FloodFillRange> ranges;

    // Construct using an image and a copy will be made to fill into,
    // Construct with BufferedImage and flood fill will write directly to
    // provided BufferedImage
    public QueueLinearFloodFiller(Bitmap img) {
        copyImage(img);
    }

    public QueueLinearFloodFiller(Bitmap img, int targetColor, int newColor) {
        useImage(img);

        setFillColor(newColor);
        setTargetColor(targetColor);
    }

    public void setTargetColor(int targetColor) {
        startColor[0] = Color.red(targetColor);
        startColor[1] = Color.green(targetColor);
        startColor[2] = Color.blue(targetColor);
        startColor[3] = Color.alpha(targetColor);
    }

    public int getFillColor() {
        return fillColor;
    }

    public void setFillColor(int value) {
        fillColor = value;
    }

    public int[] getTolerance() {
        return tolerance;
    }

    public void setTolerance(int[] value) {
        tolerance = value;
    }

    public void setTolerance(int value) {
        tolerance = new int[] { value, value, value, value };
    }

    public Bitmap getImage() {
        return image;
    }

    public void copyImage(Bitmap img) {
        // Copy data from provided Image to a BufferedImage to write flood fill
        // to, use getImage to retrieve
        // cache data in member variables to decrease overhead of property calls
        width = img.getWidth();
        height = img.getHeight();

        image = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(image);
        canvas.drawBitmap(img, 0, 0, null);

        pixels = new int[width * height];

        image.getPixels(pixels, 0, width, 0, 0, width, height);
    }

    public void useImage(Bitmap img) {
        // Use a pre-existing provided BufferedImage and write directly to it
        // cache data in member variables to decrease overhead of property calls
        width = img.getWidth();
        height = img.getHeight();
        image = img;

        pixels = new int[width * height];

        image.getPixels(pixels, 0, width, 0, 0, width, height);
    }

    protected void prepare() {
        // Called before starting flood-fill
        pixelsChecked = new boolean[pixels.length];
        ranges = new LinkedList<FloodFillRange>();
    }

    // Fills the specified point on the bitmap with the currently selected fill
    // color.
    // int x, int y: The starting coords for the fill
    public void floodFill(int x, int y) {
        // Setup
        prepare();

        if (startColor[0] == 0) {
            // ***Get starting color.
            int startPixel = pixels[(width * y) + x];
            startColor[0] = (startPixel >> 16) & 0xff;
            startColor[1] = (startPixel >> 8) & 0xff;
            startColor[2] = startPixel & 0xff;
        }

        // ***Do first call to floodfill.
        LinearFill(x, y);

        // ***Call floodfill routine while floodfill ranges still exist on the
        // queue
        FloodFillRange range;

        while (ranges.size() > 0) {
            // **Get Next Range Off the Queue
            range = ranges.remove();

            // **Check Above and Below Each Pixel in the Floodfill Range
            int downPxIdx = (width * (range.Y + 1)) + range.startX;
            int upPxIdx = (width * (range.Y - 1)) + range.startX;
            int upY = range.Y - 1;// so we can pass the y coord by ref
            int downY = range.Y + 1;

            for (int i = range.startX; i <= range.endX; i++) {
                // *Start Fill Upwards
                // if we're not above the top of the bitmap and the pixel above
                // this one is within the color tolerance
                if (range.Y > 0 && (!pixelsChecked[upPxIdx])
                        && CheckPixel(upPxIdx))
                    LinearFill(i, upY);

                // *Start Fill Downwards
                // if we're not below the bottom of the bitmap and the pixel
                // below this one is within the color tolerance
                if (range.Y < (height - 1) && (!pixelsChecked[downPxIdx])
                        && CheckPixel(downPxIdx))
                    LinearFill(i, downY);

                downPxIdx++;
                upPxIdx++;
            }
        }

        image.setPixels(pixels, 0, width, 0, 0, width, height);
    }

    // Finds the furthermost left and right boundaries of the fill area
    // on a given y coordinate, starting from a given x coordinate, filling as
    // it goes.
    // Adds the resulting horizontal range to the queue of floodfill ranges,
    // to be processed in the main loop.

    // int x, int y: The starting coords
    protected void LinearFill(int x, int y) {
        // ***Find Left Edge of Color Area
        int lFillLoc = x; // the location to check/fill on the left
        int pxIdx = (width * y) + x;

        while (true) {
            // **fill with the color
            pixels[pxIdx] = fillColor;

            // **indicate that this pixel has already been checked and filled
            pixelsChecked[pxIdx] = true;

            // **de-increment
            lFillLoc--; // de-increment counter
            pxIdx--; // de-increment pixel index

            // **exit loop if we're at edge of bitmap or color area
            if (lFillLoc < 0 || (pixelsChecked[pxIdx]) || !CheckPixel(pxIdx)) {
                break;
            }
        }

        lFillLoc++;

        // ***Find Right Edge of Color Area
        int rFillLoc = x; // the location to check/fill on the left

        pxIdx = (width * y) + x;

        while (true) {
            // **fill with the color
            pixels[pxIdx] = fillColor;

            // **indicate that this pixel has already been checked and filled
            pixelsChecked[pxIdx] = true;

            // **increment
            rFillLoc++; // increment counter
            pxIdx++; // increment pixel index

            // **exit loop if we're at edge of bitmap or color area
            if (rFillLoc >= width || pixelsChecked[pxIdx] || !CheckPixel(pxIdx)) {
                break;
            }
        }

        rFillLoc--;

        // add range to queue
        FloodFillRange r = new FloodFillRange(lFillLoc, rFillLoc, y);

        ranges.offer(r);
    }

    // Sees if a pixel is within the color tolerance range.
    protected boolean CheckPixel(int px) {
    
    int red = (pixels[px] >>> 16) & 0xff;////
    int green = (pixels[px] >>> 8) & 0xff;////
    int blue = pixels[px] & 0xff;////
    int alpha = (Color.alpha(pixels[px]));////

    return (red >= (startColor[0] - tolerance[0]) && red <= (startColor[0] + tolerance[0])////
            && green >= (startColor[1] - tolerance[1]) && green <= (startColor[1] + tolerance[1])////
            && blue >= (startColor[2] - tolerance[2]) && blue <= (startColor[2] + tolerance[2])////
            && alpha >= (startColor[3] - tolerance[3]) && alpha <= (startColor[3] + tolerance[3]));////

    }

    // Represents a linear range to be filled and branched from.
    protected class FloodFillRange {
        public int startX;
        public int endX;
        public int Y;

        public FloodFillRange(int startX, int endX, int y) {
            this.startX = startX;
            this.endX = endX;
            this.Y = y;
        }
    }
}
  
  ////Polygon is AppyBuilder Code
/**
 * A utility class for constructing and drawing rounded regular polygons.
 * https://github.com/stkent/PolygonDrawingUtil
 */
public class PolyUtil {

    private final Path backingPath = new Path();
    private final RectF tempCornerArcBounds = new RectF();

    /**
     * Draws a regular polygon.
     *
     * Note that this method is not thread safe. This is not an issue if (as is typical) all
     * invocations are made from a single thread.
     *
     * @param canvas       the {@link Canvas} to draw on
     * @param sideCount    the number of sides of the polygon
     * @param centerX      the x-coordinate of the polygon center in pixels
     * @param centerY      the y-coordinate of the polygon center in pixels
     * @param outerRadius  the distance from the polygon center to any vertex (ignoring corner
     *                      rounding) in pixels
     * @param cornerRadius the radius of the rounding applied to each corner of the polygon in
     *                      pixels
     * @param rotation     the rotation of the polygon in degrees
     * @param paint        the {@link Paint} to draw with
     */
    public void drawPolygon(
            @NonNull final Canvas canvas,
            @IntRange(from = 3) final int sideCount,
            final float centerX,
            final float centerY,
            @FloatRange(from = 0, fromInclusive = false) final float outerRadius,
            @FloatRange(from = 0) final float cornerRadius,
            final float rotation,
            @NonNull final Paint paint) {

        constructPolygonPath(
                backingPath,
                sideCount,
                centerX,
                centerY,
                outerRadius,
                cornerRadius,
                rotation);

        canvas.drawPath(backingPath, paint);
    }

    /**
     * Constructs a regular polygonal {@link Path}.
     *
     * @param path         the {@link Path} to be filled with polygon components. Will be reset.
     * @param sideCount    the number of sides of the polygon
     * @param centerX      the x-coordinate of the polygon center in pixels
     * @param centerY      the y-coordinate of the polygon center in pixels
     * @param outerRadius  the distance from the polygon center to any vertex (ignoring corner
     *                      rounding) in pixels
     * @param cornerRadius the radius of the rounding applied to each corner of the polygon in
     *                      pixels
     * @param rotation     the rotation of the polygon in degrees
     */
    public void constructPolygonPath(
            @NonNull final Path path,
            @IntRange(from = 3) final int sideCount,
            final float centerX,
            final float centerY,
            @FloatRange(from = 0, fromInclusive = false) final float outerRadius,
            @FloatRange(from = 0) final float cornerRadius,
            final float rotation) {

        path.reset();

        final float inRadius = (float) (outerRadius * Math.cos(toRadians(180.0 / sideCount)));

        if (inRadius < cornerRadius) {
            /*
             * If the supplied corner radius is too small, we default to the "incircle".
             *   - https://web.archive.org/web/20170415150442/https://en.wikipedia.org/wiki/Regular_polygon
             *   - https://web.archive.org/web/20170415150415/http://www.mathopenref.com/polygonincircle.html
             */
            path.addCircle(centerX, centerY, inRadius, Path.Direction.CW);
        } else {
            if (abs(cornerRadius) < 0.01) {
                constructNonRoundedPolygonPath(
                        path,
                        sideCount,
                        centerX,
                        centerY,
                        outerRadius);
            } else {
                constructRoundedPolygonPath(
                        path,
                        sideCount,
                        centerX,
                        centerY,
                        outerRadius,
                        cornerRadius);
            }

            final Matrix rotationMatrix = new Matrix();
            rotationMatrix.setRotate(rotation, centerX, centerY);
            path.transform(rotationMatrix);
        }
    }

    private void constructNonRoundedPolygonPath(
            @NonNull final Path path,
            @IntRange(from = 3) final int sideCount,
            final float centerX,
            final float centerY,
            @FloatRange(from = 0, fromInclusive = false) final float outerRadius) {

        for (int cornerNumber = 0; cornerNumber < sideCount; cornerNumber++) {
            final double angleToCorner = cornerNumber * (360.0 / sideCount);
            final float cornerX = (float) (centerX + outerRadius * cos(toRadians(angleToCorner)));
            final float cornerY = (float) (centerY + outerRadius * sin(toRadians(angleToCorner)));

            if (cornerNumber == 0) {
                path.moveTo(cornerX, cornerY);
            } else {
                path.lineTo(cornerX, cornerY);
            }
        }

        // Draw the final straight edge.
        path.close();
    }

    private void constructRoundedPolygonPath(
            @NonNull final Path path,
            @IntRange(from = 3) final int sideCount,
            final float centerX,
            final float centerY,
            @FloatRange(from = 0, fromInclusive = false) final float outerRadius,
            @FloatRange(from = 0) final float cornerRadius) {

        final double halfInteriorCornerAngle = 90 - (180.0 / sideCount);
        final float halfCornerArcSweepAngle = (float) (90 - halfInteriorCornerAngle);
        final double distanceToCornerArcCenter = outerRadius - cornerRadius / sin(toRadians(halfInteriorCornerAngle));

        for (int cornerNumber = 0; cornerNumber < sideCount; cornerNumber++) {
            final double angleToCorner = cornerNumber * (360.0 / sideCount);
            final float cornerCenterX = (float) (centerX + distanceToCornerArcCenter * cos(toRadians(angleToCorner)));
            final float cornerCenterY = (float) (centerY + distanceToCornerArcCenter * sin(toRadians(angleToCorner)));

            tempCornerArcBounds.set(
                    cornerCenterX - cornerRadius,
                    cornerCenterY - cornerRadius,
                    cornerCenterX + cornerRadius,
                    cornerCenterY + cornerRadius);

            /*
             * Quoted from the arcTo documentation:
             *
             *   "Append the specified arc to the path as a new contour. If the start of the path is different from the
             *    path's current last point, then an automatic lineTo() is added to connect the current contour to the
             *    start of the arc. However, if the path is empty, then we call moveTo() with the first point of the
             *    arc."
             *
             * We construct our polygon by sequentially drawing rounded corners using arcTo, and leverage the
             * automatically-added moveTo/lineTo instructions to connect these corners with straight edges.
             */
            path.arcTo(
                    tempCornerArcBounds,
                    (float) (angleToCorner - halfCornerArcSweepAngle),
                    2 * halfCornerArcSweepAngle);
        }

        // Draw the final straight edge.
        path.close();
    }

    private double toRadians(final double degrees) {
        return 2 * PI * degrees / 360;
    }

}
  
  private boolean allowFill(int ignoreColor, int targetColor, int allowTolerance){
    int[] tolerance = new int[] { 0, 0, 0, 0 };
    tolerance = new int[] { allowTolerance, allowTolerance, allowTolerance, allowTolerance };
    
    int[] startColor = new int[] { 0, 0, 0, 0 };
    startColor[0] = Color.red(targetColor);
    startColor[1] = Color.green(targetColor);
    startColor[2] = Color.blue(targetColor);
    startColor[3] = Color.alpha(targetColor);
    
    int[] iColor = new int[] { 0, 0, 0, 0 };
    iColor[0] = Color.red(ignoreColor);
    iColor[1] = Color.green(ignoreColor);
    iColor[2] = Color.blue(ignoreColor);
    iColor[3] = Color.alpha(ignoreColor);
    
    int red = startColor[0];//targetColor >>> 16 & 0xff;////
    int green = startColor[1];//targetColor >>> 8 & 0xff;////
    int blue = startColor[2];//targetColor & 0xff;////
    int alpha = startColor[3];//Color.alpha(targetColor);////

    return !(red >= (iColor[0] - tolerance[0]) && red <= (iColor[0] + tolerance[0])////
            && green >= (iColor[1] - tolerance[1]) && green <= (iColor[1] + tolerance[1])////
            && blue >= (iColor[2] - tolerance[2]) && blue <= (iColor[2] + tolerance[2])////
            && alpha >= (iColor[3] - tolerance[3]) && alpha <= (iColor[3] + tolerance[3]));////
  }
  
  public static Bitmap getBitmapFromAsset(Context context, String filePath) {
    AssetManager assetManager = context.getAssets();

    InputStream istr;
    Bitmap bitmap = null;
    try {
      istr = assetManager.open(filePath);
      bitmap = BitmapFactory.decodeStream(istr);
    } catch (IOException e) {
      // handle exception
    }    return bitmap;
    }
  
  ////
  @SimpleProperty(
      description = "The color of the canvas background.",
      category = PropertyCategory.APPEARANCE)
  public int BackgroundColor() {
    return backgroundColor;
  }

  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR,
      defaultValue = Component.DEFAULT_VALUE_COLOR_WHITE)
  @SimpleProperty
  public void BackgroundColor(int argb) {
    backgroundColor = argb;
  }
  
  @SimpleProperty(
      description = "The color in which lines are drawn",
      category = PropertyCategory.APPEARANCE)
  public int PaintColor() {
    return paintColor;
  }

  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR,
      defaultValue = Component.DEFAULT_VALUE_COLOR_BLACK)
  @SimpleProperty
  public void PaintColor(int argb) {
    paintColor = argb;
    changePaint(paint, argb);
  }

  private void changePaint(Paint paint, int argb) {
    if (argb == Component.COLOR_DEFAULT) {
      // The default paint color is black.
      paint.setColor(Color.BLACK);
    } else if (argb == Component.COLOR_NONE) {
      paint.setAlpha(0x00);
      paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.CLEAR));
    } else {
      paint.setColor(argb);// & 0x00FFFFFF);
      //paint.setAlpha((argb >> 24) & 0xFF);
    }
  }
  
  @SimpleProperty(
      description = "The width of lines drawn on the canvas.",
      category = PropertyCategory.APPEARANCE)
  public float LineWidth() {
    return paint.getStrokeWidth() / Density();
  }

  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_FLOAT,
      defaultValue = "5")
  @SimpleProperty
  public void LineWidth(float width) {
    paint.setStrokeWidth(width * Density());
  }
  
  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_FLOAT , defaultValue = "500")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public void MaxZoom(float maxZoom) {
        this.maxZoom = maxZoom / 100;
    }

    @SimpleProperty
    public float MaxZoom() {
        return maxZoom;
    }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Enable/Disable AntiAlias")
  public boolean AntiAlias() {
    return antiAlias;
  }

  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN, defaultValue = "True")
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  public void AntiAlias(boolean antiAlias) {
    this.antiAlias = antiAlias;
    if (antiAlias){
      paint.setAntiAlias(true);
    } else {
      paint.setAntiAlias(false);
    }
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Enable/Disable Rounded")
  public boolean Rounded() {
    return rounded;
  }

  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN, defaultValue = "True")
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  public void Rounded(boolean rounded) {
    this.rounded = rounded;
    if (rounded){
        paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
      } else {
        paint.setStrokeJoin(Paint.Join.MITER);
		paint.setStrokeCap(Paint.Cap.BUTT);
      }
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Enable/Disable Rounded")
  public boolean Texture() {
    return texture;
  }

  //@DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN, defaultValue = "False")
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  public void Texture(boolean texture) {
    this.texture = texture;
    if (texture){
      if (mirror) {
      if (!TextureImagePath.startsWith("//")) {
          Bitmap bitmap = getBitmapFromAsset(context, TextureImagePath);
          Shader shader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
          paint.setShader(shader);
      } else if (TextureImagePath.startsWith("file:///android_asset/")) {
          String[] textureFile = TextureImagePath.split("file:///android_asset/");
          Bitmap bitmap = getBitmapFromAsset(context, textureFile[1]);
          Shader shader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
          paint.setShader(shader);
      } else {
        File file = new File(TextureImagePath);
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        Shader shader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        paint.setShader(shader);
      }
        
      } else {
        if (!TextureImagePath.contains("//")) {
          Bitmap bitmap = getBitmapFromAsset(context, TextureImagePath);
          Shader shader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
          paint.setShader(shader);
        } else if (TextureImagePath.startsWith("file:///android_asset/")) {
          String[] textureFile = TextureImagePath.split("file:///android_asset/");
          Bitmap bitmap = getBitmapFromAsset(context, textureFile[1]);
          Shader shader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
          paint.setShader(shader);
        } else {
          File file = new File(TextureImagePath);
          Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
          Shader shader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
          paint.setShader(shader);
        }
      }
    } else {
        paint.setShader(null);
    }
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Enable/Disable Mirror")
  public boolean Mirror() {
    return mirror;
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  public void Mirror(boolean mirror) {
    this.mirror = mirror;
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE)
  public String TextureImage()
  {
    return this.TextureImagePath;
  }
  
  @DesignerProperty(editorType="asset", defaultValue="")
  @SimpleProperty
  public void TextureImage(String path)
  {
    this.TextureImagePath = (path == null ? "" : path);
  }
  
  @SimpleProperty(description = "The font size of text drawn on the canvas.", category = PropertyCategory.APPEARANCE)
  public float FontSize() {
    float scale = Density();
    return paint.getTextSize() / scale;
  }

  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_FLOAT, defaultValue = Component.FONT_DEFAULT_SIZE + "")
  @SimpleProperty
  public void FontSize(float size) {
    float scale = Density();
    paint.setTextSize(size * scale);
  }
  
}