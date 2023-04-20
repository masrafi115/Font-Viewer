package com.codesw.fontviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.widget.LinearLayout;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import android.widget.TextView;
import android.widget.ScrollView;
import android.widget.ImageView;
import android.widget.SeekBar;
import com.google.android.material.button.*;
import android.app.Activity;
import android.content.SharedPreferences;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class ViewActivity extends  AppCompatActivity  { 
	
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private DrawerLayout _drawer;
	private String mPath = "";
	private String Dummy = "";
	private double color1 = 0;
	private double color2 = 0;
	private double color3 = 0;
	private String RgbToHex = "";
	private String Rgb_To_Hex = "";
	private double color_1 = 0;
	private double color_2 = 0;
	private double color_3 = 0;
	private boolean previewed = false;
	private String samp = "";
	private double textSize = 0;
	
	private LinearLayout linear1;
	private TextView textview1;
	private LinearLayout _drawer_linear1;
	private ScrollView _drawer_vscroll1;
	private TextView _drawer_textview1;
	private LinearLayout _drawer_linear3;
	private LinearLayout _drawer_linear5;
	private LinearLayout _drawer_linear6;
	private LinearLayout _drawer_linear7;
	private LinearLayout _drawer_linear21;
	private LinearLayout _drawer_linear13;
	private LinearLayout _drawer_linear22;
	private LinearLayout _drawer_linear19;
	private LinearLayout _drawer_linear4;
	private LinearLayout _drawer_linear23;
	private TextView _drawer_textview2;
	private ImageView _drawer_imageview1;
	private SeekBar _drawer_seekbar1;
	private TextView _drawer_textview23;
	private LinearLayout _drawer_linear8;
	private LinearLayout _drawer_linear9;
	private LinearLayout _drawer_linear10;
	private LinearLayout _drawer_linear11;
	private LinearLayout _drawer_linear12;
	private TextView _drawer_textview3;
	private TextView _drawer_textview5;
	private ImageView _drawer_imageview2;
	private TextView _drawer_textview4;
	private SeekBar _drawer_seekbar2;
	private TextView _drawer_textview6;
	private TextView _drawer_textview7;
	private SeekBar _drawer_seekbar5;
	private TextView _drawer_textview8;
	private TextView _drawer_textview9;
	private SeekBar _drawer_seekbar6;
	private TextView _drawer_textview10;
	private TextView _drawer_textview11;
	private SeekBar _drawer_seekbar7;
	private TextView _drawer_textview12;
	private LinearLayout _drawer_linear14;
	private LinearLayout _drawer_linear15;
	private LinearLayout _drawer_linear16;
	private LinearLayout _drawer_linear17;
	private LinearLayout _drawer_linear18;
	private TextView _drawer_textview13;
	private TextView _drawer_textview14;
	private ImageView _drawer_imageview3;
	private TextView _drawer_textview15;
	private SeekBar _drawer_seekbar8;
	private TextView _drawer_textview16;
	private TextView _drawer_textview17;
	private SeekBar _drawer_seekbar9;
	private TextView _drawer_textview18;
	private TextView _drawer_textview19;
	private SeekBar _drawer_seekbar10;
	private TextView _drawer_textview20;
	private TextView _drawer_textview21;
	private SeekBar _drawer_seekbar11;
	private TextView _drawer_textview22;
	private LinearLayout _drawer_linear20;
	private MaterialButton _drawer_materialbutton2;
	private MaterialButton _drawer_materialbutton1;
	private ImageView _drawer_imageview4;
	
	private SharedPreferences file;
	private SharedPreferences settings;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.view);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		_app_bar = (AppBarLayout) findViewById(R.id._app_bar);
		_coordinator = (CoordinatorLayout) findViewById(R.id._coordinator);
		_toolbar = (Toolbar) findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		_drawer = (DrawerLayout) findViewById(R.id._drawer);
		ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(ViewActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
		_drawer.addDrawerListener(_toggle);
		_toggle.syncState();
		
		LinearLayout _nav_view = (LinearLayout) findViewById(R.id._nav_view);
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		textview1 = (TextView) findViewById(R.id.textview1);
		_drawer_linear1 = (LinearLayout) _nav_view.findViewById(R.id.linear1);
		_drawer_vscroll1 = (ScrollView) _nav_view.findViewById(R.id.vscroll1);
		_drawer_textview1 = (TextView) _nav_view.findViewById(R.id.textview1);
		_drawer_linear3 = (LinearLayout) _nav_view.findViewById(R.id.linear3);
		_drawer_linear5 = (LinearLayout) _nav_view.findViewById(R.id.linear5);
		_drawer_linear6 = (LinearLayout) _nav_view.findViewById(R.id.linear6);
		_drawer_linear7 = (LinearLayout) _nav_view.findViewById(R.id.linear7);
		_drawer_linear21 = (LinearLayout) _nav_view.findViewById(R.id.linear21);
		_drawer_linear13 = (LinearLayout) _nav_view.findViewById(R.id.linear13);
		_drawer_linear22 = (LinearLayout) _nav_view.findViewById(R.id.linear22);
		_drawer_linear19 = (LinearLayout) _nav_view.findViewById(R.id.linear19);
		_drawer_linear4 = (LinearLayout) _nav_view.findViewById(R.id.linear4);
		_drawer_linear23 = (LinearLayout) _nav_view.findViewById(R.id.linear23);
		_drawer_textview2 = (TextView) _nav_view.findViewById(R.id.textview2);
		_drawer_imageview1 = (ImageView) _nav_view.findViewById(R.id.imageview1);
		_drawer_seekbar1 = (SeekBar) _nav_view.findViewById(R.id.seekbar1);
		_drawer_textview23 = (TextView) _nav_view.findViewById(R.id.textview23);
		_drawer_linear8 = (LinearLayout) _nav_view.findViewById(R.id.linear8);
		_drawer_linear9 = (LinearLayout) _nav_view.findViewById(R.id.linear9);
		_drawer_linear10 = (LinearLayout) _nav_view.findViewById(R.id.linear10);
		_drawer_linear11 = (LinearLayout) _nav_view.findViewById(R.id.linear11);
		_drawer_linear12 = (LinearLayout) _nav_view.findViewById(R.id.linear12);
		_drawer_textview3 = (TextView) _nav_view.findViewById(R.id.textview3);
		_drawer_textview5 = (TextView) _nav_view.findViewById(R.id.textview5);
		_drawer_imageview2 = (ImageView) _nav_view.findViewById(R.id.imageview2);
		_drawer_textview4 = (TextView) _nav_view.findViewById(R.id.textview4);
		_drawer_seekbar2 = (SeekBar) _nav_view.findViewById(R.id.seekbar2);
		_drawer_textview6 = (TextView) _nav_view.findViewById(R.id.textview6);
		_drawer_textview7 = (TextView) _nav_view.findViewById(R.id.textview7);
		_drawer_seekbar5 = (SeekBar) _nav_view.findViewById(R.id.seekbar5);
		_drawer_textview8 = (TextView) _nav_view.findViewById(R.id.textview8);
		_drawer_textview9 = (TextView) _nav_view.findViewById(R.id.textview9);
		_drawer_seekbar6 = (SeekBar) _nav_view.findViewById(R.id.seekbar6);
		_drawer_textview10 = (TextView) _nav_view.findViewById(R.id.textview10);
		_drawer_textview11 = (TextView) _nav_view.findViewById(R.id.textview11);
		_drawer_seekbar7 = (SeekBar) _nav_view.findViewById(R.id.seekbar7);
		_drawer_textview12 = (TextView) _nav_view.findViewById(R.id.textview12);
		_drawer_linear14 = (LinearLayout) _nav_view.findViewById(R.id.linear14);
		_drawer_linear15 = (LinearLayout) _nav_view.findViewById(R.id.linear15);
		_drawer_linear16 = (LinearLayout) _nav_view.findViewById(R.id.linear16);
		_drawer_linear17 = (LinearLayout) _nav_view.findViewById(R.id.linear17);
		_drawer_linear18 = (LinearLayout) _nav_view.findViewById(R.id.linear18);
		_drawer_textview13 = (TextView) _nav_view.findViewById(R.id.textview13);
		_drawer_textview14 = (TextView) _nav_view.findViewById(R.id.textview14);
		_drawer_imageview3 = (ImageView) _nav_view.findViewById(R.id.imageview3);
		_drawer_textview15 = (TextView) _nav_view.findViewById(R.id.textview15);
		_drawer_seekbar8 = (SeekBar) _nav_view.findViewById(R.id.seekbar8);
		_drawer_textview16 = (TextView) _nav_view.findViewById(R.id.textview16);
		_drawer_textview17 = (TextView) _nav_view.findViewById(R.id.textview17);
		_drawer_seekbar9 = (SeekBar) _nav_view.findViewById(R.id.seekbar9);
		_drawer_textview18 = (TextView) _nav_view.findViewById(R.id.textview18);
		_drawer_textview19 = (TextView) _nav_view.findViewById(R.id.textview19);
		_drawer_seekbar10 = (SeekBar) _nav_view.findViewById(R.id.seekbar10);
		_drawer_textview20 = (TextView) _nav_view.findViewById(R.id.textview20);
		_drawer_textview21 = (TextView) _nav_view.findViewById(R.id.textview21);
		_drawer_seekbar11 = (SeekBar) _nav_view.findViewById(R.id.seekbar11);
		_drawer_textview22 = (TextView) _nav_view.findViewById(R.id.textview22);
		_drawer_linear20 = (LinearLayout) _nav_view.findViewById(R.id.linear20);
		_drawer_materialbutton2 = (MaterialButton) _nav_view.findViewById(R.id.materialbutton2);
		_drawer_materialbutton1 = (MaterialButton) _nav_view.findViewById(R.id.materialbutton1);
		_drawer_imageview4 = (ImageView) _nav_view.findViewById(R.id.imageview4);
		file = getSharedPreferences("file", Activity.MODE_PRIVATE);
		settings = getSharedPreferences("settings", Activity.MODE_PRIVATE);
		
		_drawer_seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged (SeekBar _param1, int _param2, boolean _param3) {
				final int _progressValue = _param2;
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar _param1) {
				
			}
			
			@Override
			public void onStopTrackingTouch(SeekBar _param2) {
				
			}
		});
	}
	
	private void initializeLogic() {
		_setDrawerWidth(250);
		mPath = getIntent().getStringExtra("path");
		if (settings.getString("dummy_text", "").equals("")) {
			Dummy = "Loram Ipsum dolor sit amet";
			textview1.setText(Dummy);
		}
		else {
			Dummy = settings.getString("dummy_text", "");
			textview1.setText(Dummy);
		}
		if (settings.getString("text_size", "").equals("")) {
			textSize = 25;
			_drawer_textview1.setTextSize((int) textSize);
							textview1.setTextSize((int) textSize);
							_drawer_textview23.setText(String.valueOf((long)(textSize)));
		}
		else {
			textSize = Double.parseDouble(settings.getString("text_size", ""));
			_drawer_textview1.setTextSize((int) textSize);
							textview1.setTextSize((int) textSize);
							_drawer_textview23.setText(String.valueOf((long)(textSize)));
		}
		if (settings.getString("align", "").equals("")) {
			
		}
		else {
			
		}
		textview1.setTypeface(Typeface.createFromFile(mPath));
		_Elevation(_drawer_linear1, 5);
		color_1 = 255;
		color_2 = 255;
		color_3 = 255;
		Rgb_To_Hex = String.format("#%02x%02x%02x",(int)color_1,(int)color_2,(int)color_3);
		_drawer_textview14.setText(Rgb_To_Hex);
		_setBackgroud_RGB(_drawer_linear1, color_1, color_2, color_3);
		color1 = 0;
		color2 = 0;
		color3 = 0;
		RgbToHex = String.format("#%02x%02x%02x",(int)color1,(int)color2,(int)color3);
		_drawer_textview5.setText(RgbToHex);
		_setBackground_Text(_drawer_textview5, color1, color2, color3);
		_drawer_seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
						@Override
						public void onProgressChanged (SeekBar _param1, int _param2, boolean _param3) {
								final int _progressValue = _param2;
								_drawer_textview1.setTextSize((int)_progressValue);
								textview1.setTextSize((int)_progressValue);
								_drawer_textview23.setText(String.valueOf((long)(_progressValue)));
						}
						
						@Override
						public void onStartTrackingTouch(SeekBar _param1) {
								
						}
						
						@Override
						public void onStopTrackingTouch(SeekBar _param2) {
								
						}
				});
				
				_drawer_seekbar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
						@Override
						public void onProgressChanged (SeekBar _param1, int _param2, boolean _param3) {
								final int _progressValue = _param2;
								color1 = _progressValue;
								_drawer_textview8.setText(String.valueOf((long)(_progressValue)));
								_setBackground_Text(_drawer_textview1, _progressValue, color2, color3);
								_setBackground_Text(textview1, _progressValue, color2, color3);
						}
						
						@Override
						public void onStartTrackingTouch(SeekBar _param1) {
								
						}
						
						@Override
						public void onStopTrackingTouch(SeekBar _param2) {
								
						}
				});
				
				_drawer_seekbar6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
						@Override
						public void onProgressChanged (SeekBar _param1, int _param2, boolean _param3) {
								final int _progressValue = _param2;
								color2 = _progressValue;
								_drawer_textview10.setText(String.valueOf((long)(_progressValue)));
								_setBackground_Text(_drawer_textview1, color1, _progressValue, color3);
								_setBackground_Text(textview1, color1, _progressValue, color3);
						}
						
						@Override
						public void onStartTrackingTouch(SeekBar _param1) {
								
						}
						
						@Override
						public void onStopTrackingTouch(SeekBar _param2) {
								
						}
				});
				
				_drawer_seekbar7.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
						@Override
						public void onProgressChanged (SeekBar _param1, int _param2, boolean _param3) {
								final int _progressValue = _param2;
								color3 = _progressValue;
								_drawer_textview12.setText(String.valueOf((long)(_progressValue)));
								_setBackground_Text(_drawer_textview1, color1, color2, _progressValue);
								_setBackground_Text(textview1, color1, color2, _progressValue);
						}
						
						@Override
						public void onStartTrackingTouch(SeekBar _param1) {
								
						}
						
						@Override
						public void onStopTrackingTouch(SeekBar _param2) {
								
						}
				});
				
				_drawer_seekbar9.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
						@Override
						public void onProgressChanged (SeekBar _param1, int _param2, boolean _param3) {
								final int _progressValue = _param2;
								color_1 = _progressValue;
								_drawer_textview18.setText(String.valueOf((long)(_progressValue)));
								_setBackgroud_RGB(_drawer_linear1, _progressValue, color_2, color_3);
								_setBackgroud_RGB(linear1, _progressValue, color_2, color_3);
						}
						
						@Override
						public void onStartTrackingTouch(SeekBar _param1) {
								
						}
						
						@Override
						public void onStopTrackingTouch(SeekBar _param2) {
								
						}
				});
				
				_drawer_seekbar10.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
						@Override
						public void onProgressChanged (SeekBar _param1, int _param2, boolean _param3) {
								final int _progressValue = _param2;
								color_2 = _progressValue;
								_drawer_textview20.setText(String.valueOf((long)(_progressValue)));
								_setBackgroud_RGB(_drawer_linear1, color_1, _progressValue, color_3);
								_setBackgroud_RGB(linear1, color_1, _progressValue, color_3);
						}
						
						@Override
						public void onStartTrackingTouch(SeekBar _param1) {
								
						}
						
						@Override
						public void onStopTrackingTouch(SeekBar _param2) {
								
						}
				});
				
				_drawer_seekbar11.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
						@Override
						public void onProgressChanged (SeekBar _param1, int _param2, boolean _param3) {
								final int _progressValue = _param2;
								color_3 = _progressValue;
								_drawer_textview22.setText(String.valueOf((long)(_progressValue)));
								_setBackgroud_RGB(_drawer_linear1, color_1, color_2, _progressValue);
								_setBackgroud_RGB(linear1, color_1, color_2, _progressValue);
						}
						
						@Override
						public void onStartTrackingTouch(SeekBar _param1) {
								
						}
						
						@Override
						public void onStopTrackingTouch(SeekBar _param2) {
								
						}
				});
				
				_drawer_materialbutton2.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
								_drawer_imageview4.setImageBitmap(getBitmapFromView(linear1));
						}
				});
				
				_drawer_materialbutton1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
								if (previewed) {
										storeImage(getBitmapFromView(linear1));
								}
						}
				});
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		if (_drawer.isDrawerOpen(GravityCompat.START)) {
			_drawer.closeDrawer(GravityCompat.START);
		}
		else {
			super.onBackPressed();
		}
	}
	public void _setBackgroud_RGB (final View _target, final double _Red, final double _Green, final double _Blue) {
		_target.setBackgroundColor(android.graphics.Color.rgb((int)_Red,(int)_Green,(int)_Blue));
		
		Rgb_To_Hex = String.format("#%02x%02x%02x",(int)_Red,(int)_Green,(int)_Blue);
		
		_drawer_textview14.setText(Rgb_To_Hex);
	}
	
	
	public void _setBackground_Text (final TextView _text, final double _Red, final double _Green, final double _Blue) {
		_text.setTextColor(android.graphics.Color.rgb((int)_Red,(int)_Green,(int)_Blue));
		
		RgbToHex = String.format("#%02x%02x%02x",(int)_Red,(int)_Green,(int)_Blue);
		
		_drawer_textview5.setText(RgbToHex);
	}
	
	
	public void _Elevation (final View _view, final double _number) {
		
		_view.setElevation((int)_number);
	}
	
	
	public void _LayoutToBitmap () {
	}
	private void storeImage(Bitmap image) { java.io.File pictureFile = new java.io.File(getExternalCacheDir() + "/image.jpg");
		if (pictureFile == null) { Log.d("MainActivity", "Error creating media file, check storage permissions: ");
			return; } try {
			java.io.FileOutputStream fos = new java.io.FileOutputStream(pictureFile); image.compress(Bitmap.CompressFormat.PNG, 90, fos);
			fos.close(); } catch (java.io.FileNotFoundException e) { Log.d("MainActivity", "File not found: " + e.getMessage()); } catch (java.io.IOException e) { Log.d("MainActivity", "Error accessing file: " + e.getMessage());
		}
		
		Intent iten = new Intent(android.content.Intent.ACTION_SEND);
		iten.setType("*/*");
		iten.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new java.io.File(getExternalCacheDir() + "/image.jpg")));
		startActivity(Intent.createChooser(iten, "Share With Your Friends􎭣"));
	}
	
	private Bitmap getBitmapFromView(View view) { Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(returnedBitmap);
		android.graphics.drawable.Drawable bgDrawable =view.getBackground();
		if (bgDrawable!=null) { bgDrawable.draw(canvas); } else{ canvas.drawColor(Color.WHITE); }
		view.draw(canvas);
		return returnedBitmap;
		
		
		//button onClick
		
		
	}
	
	
	public void _setDrawerWidth (final double _num) {
		LinearLayout _nav_view = (LinearLayout) findViewById(R.id._nav_view);
		
		_nav_view.setBackgroundColor(Color.parseColor("#FFFFFF"));
		
		androidx.drawerlayout.widget.DrawerLayout.LayoutParams params = (androidx.drawerlayout.widget.DrawerLayout.LayoutParams)_nav_view.getLayoutParams();
		
		params.width = (int)getDip((int)_num);
		
		params.height = androidx.drawerlayout.widget.DrawerLayout.LayoutParams.MATCH_PARENT;
		
		_nav_view.setLayoutParams(params);
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}