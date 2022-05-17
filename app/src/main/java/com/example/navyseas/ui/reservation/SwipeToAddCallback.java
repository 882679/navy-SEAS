package com.example.navyseas.ui.reservation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navyseas.R;

abstract public class SwipeToAddCallback extends ItemTouchHelper.Callback {
	private final Paint mClearPaint;
	private final ColorDrawable mBackground;
	private final int backgroundColor;
	private final Drawable deleteDrawable;
	private final int intrinsicWidth;
	private final int intrinsicHeight;
	Context mContext;

	SwipeToAddCallback(Context context) {
		mContext = context;
		mBackground = new ColorDrawable();
		backgroundColor = Color.parseColor("#00c853");
		mClearPaint = new Paint();
		mClearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
		deleteDrawable = ContextCompat.getDrawable(mContext, R.drawable.add_icon);
		assert deleteDrawable != null;
		intrinsicWidth = deleteDrawable.getIntrinsicWidth();
		intrinsicHeight = deleteDrawable.getIntrinsicHeight();
	}


	@Override
	public int getMovementFlags(@NonNull RecyclerView recyclerView,
	                            @NonNull RecyclerView.ViewHolder viewHolder) {
		return makeMovementFlags(0, ItemTouchHelper.LEFT);
	}

	@Override
	public boolean onMove(@NonNull RecyclerView recyclerView,
	                      @NonNull RecyclerView.ViewHolder viewHolder,
	                      @NonNull RecyclerView.ViewHolder viewHolder1) {
		return false;
	}

	@Override
	public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
	                        @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
	                        int actionState, boolean isCurrentlyActive) {
		super.onChildDraw(c, recyclerView, viewHolder,
				dX, dY, actionState, isCurrentlyActive);

		View itemView = viewHolder.itemView;
		int itemHeight = itemView.getHeight();

		boolean isCancelled = dX == 0 && !isCurrentlyActive;

		if (isCancelled) {
			clearCanvas(c, itemView.getRight() + dX, (float) itemView.getTop(),
					(float) itemView.getRight(), (float) itemView.getBottom());
			super.onChildDraw(c, recyclerView, viewHolder,
					dX, dY, actionState, false);
			return;
		}

		mBackground.setColor(backgroundColor);
		mBackground.setBounds(
				itemView.getRight() + (int) dX, itemView.getTop()+20,
				itemView.getRight(),
				itemView.getBottom());
		mBackground.draw(c);

		int deleteIconTop = (itemView.getTop() + (itemHeight - intrinsicHeight) / 2) + 10;
		int deleteIconMargin = (itemHeight - intrinsicHeight) / 2;
		int deleteIconLeft = itemView.getRight() - deleteIconMargin - intrinsicWidth;
		int deleteIconRight = itemView.getRight() - deleteIconMargin;
		int deleteIconBottom = deleteIconTop + intrinsicHeight;

		deleteDrawable.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom);
		deleteDrawable.draw(c);

		super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
	}

	private void clearCanvas(Canvas c, Float left, Float top, Float right, Float bottom) {
		c.drawRect(left, top, right, bottom, mClearPaint);
	}

	@Override
	public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
		return 0.7f;
	}
}

