package willow.getSimplerocketsShip.koishi.view;
import android.support.design.widget.FloatingActionButton;
import android.content.Context;
import android.util.AttributeSet;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.support.v4.view.ViewPropertyAnimatorListener;


public class MyFABBehavior extends FloatingActionButton.Behavior
{

	private boolean isAnimatingOut;
	public MyFABBehavior(Context context, AttributeSet attrs) {
        super();
    }
	public boolean onStartNestedScroll(CoordinatorLayout l, FloatingActionButton c, View directTargetChild, View v, int nestedScrollAxes){
		return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
	}
	public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
							   View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
		if ((dyConsumed > 0 || dyUnconsumed > 0) && !isAnimatingOut
			&& child.getVisibility() == View.VISIBLE) {// 手指上滑，隐藏FAB
		//	scaleHide(child,listener);
		child.hide();
		} else if ((dyConsumed < 0 || dyUnconsumed < 0) && child.getVisibility() != View.VISIBLE) {
			//AnimatorUtil.scaleShow(child, null);// 手指下滑，显示FAB
			child.show();
		}
	}
	private ViewPropertyAnimatorListener listener = new ViewPropertyAnimatorListener() {

		@Override
		public void onAnimationStart(View view) {
			isAnimatingOut = true;
		}

		@Override
		public void onAnimationEnd(View view) {
			isAnimatingOut = false;
			view.setVisibility(View.GONE);
		}

		@Override
		public void onAnimationCancel(View arg0) {
			isAnimatingOut = false;
		}
};
}

