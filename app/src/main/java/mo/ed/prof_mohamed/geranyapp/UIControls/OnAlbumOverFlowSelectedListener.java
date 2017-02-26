package mo.ed.prof_mohamed.geranyapp.UIControls;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.PopupMenu;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

import mo.ed.prof_mohamed.geranyapp.R;

/**
 * Created by Prof-Mohamed on 2/16/2017.
 */
public class OnAlbumOverFlowSelectedListener implements View.OnClickListener {

//    private Album album;
    private Context mContext;

    public OnAlbumOverFlowSelectedListener(Context context) {
        mContext=context;
    }

    @Override
    public void onClick(View v) {
        PopupMenu popupMenu = new PopupMenu(mContext, v) {
            @Override
            public void setOnMenuItemClickListener(@Nullable OnMenuItemClickListener listener) {

                listener.onMenuItemClick(new MenuItem() {
                    @Override
                    public int getItemId() {
                        return 0;
                    }

                    @Override
                    public int getGroupId() {
                        return 0;
                    }

                    @Override
                    public int getOrder() {
                        return 0;
                    }

                    @Override
                    public MenuItem setTitle(CharSequence title) {
                        return null;
                    }

                    @Override
                    public MenuItem setTitle(int title) {
                        return null;
                    }

                    @Override
                    public CharSequence getTitle() {
                        return null;
                    }

                    @Override
                    public MenuItem setTitleCondensed(CharSequence title) {
                        return null;
                    }

                    @Override
                    public CharSequence getTitleCondensed() {
                        return null;
                    }

                    @Override
                    public MenuItem setIcon(Drawable icon) {
                        return null;
                    }

                    @Override
                    public MenuItem setIcon(int iconRes) {
                        return null;
                    }

                    @Override
                    public Drawable getIcon() {
                        return null;
                    }

                    @Override
                    public MenuItem setIntent(Intent intent) {
                        return null;
                    }

                    @Override
                    public Intent getIntent() {
                        return null;
                    }

                    @Override
                    public MenuItem setShortcut(char numericChar, char alphaChar) {
                        return null;
                    }

                    @Override
                    public MenuItem setNumericShortcut(char numericChar) {
                        return null;
                    }

                    @Override
                    public char getNumericShortcut() {
                        return 0;
                    }

                    @Override
                    public MenuItem setAlphabeticShortcut(char alphaChar) {
                        return null;
                    }

                    @Override
                    public char getAlphabeticShortcut() {
                        return 0;
                    }

                    @Override
                    public MenuItem setCheckable(boolean checkable) {
                        return null;
                    }

                    @Override
                    public boolean isCheckable() {
                        return false;
                    }

                    @Override
                    public MenuItem setChecked(boolean checked) {
                        return null;
                    }

                    @Override
                    public boolean isChecked() {
                        return false;
                    }

                    @Override
                    public MenuItem setVisible(boolean visible) {
                        return null;
                    }

                    @Override
                    public boolean isVisible() {
                        return false;
                    }

                    @Override
                    public MenuItem setEnabled(boolean enabled) {
                        return null;
                    }

                    @Override
                    public boolean isEnabled() {
                        return false;
                    }

                    @Override
                    public boolean hasSubMenu() {
                        return false;
                    }

                    @Override
                    public SubMenu getSubMenu() {
                        return null;
                    }

                    @Override
                    public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener menuItemClickListener) {
                        return null;
                    }

                    @Override
                    public ContextMenu.ContextMenuInfo getMenuInfo() {
                        return null;
                    }

                    @Override
                    public void setShowAsAction(int actionEnum) {

                    }

                    @Override
                    public MenuItem setShowAsActionFlags(int actionEnum) {
                        return null;
                    }

                    @Override
                    public MenuItem setActionView(View view) {
                        return null;
                    }

                    @Override
                    public MenuItem setActionView(int resId) {
                        return null;
                    }

                    @Override
                    public View getActionView() {
                        return null;
                    }

                    @Override
                    public MenuItem setActionProvider(ActionProvider actionProvider) {
                        return null;
                    }

                    @Override
                    public ActionProvider getActionProvider() {
                        return null;
                    }

                    @Override
                    public boolean expandActionView() {
                        return false;
                    }

                    @Override
                    public boolean collapseActionView() {
                        return false;
                    }

                    @Override
                    public boolean isActionViewExpanded() {
                        return false;
                    }

                    @Override
                    public MenuItem setOnActionExpandListener(OnActionExpandListener listener) {
                        return null;
                    }
                });
                super.setOnMenuItemClickListener(listener);
            }

//            @Override
//            public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.edit_post:
////                        deleteAlbum(mAlbum);
//                        return true;
//
//                    case R.id.remove_post:
////                        renameAlbum(mAlbum);
//                        return true;
//
////                    case R.id.album_overflow_lock:
////                        lockAlbum(mAlbum);
////                        return true;
//
////                    case R.id.album_overflow_unlock:
////                        unlockAlbum(mAlbum);
////                        return true;
//
////                    case R.id.album_overflow_set_cover:
////                        setAlbumCover(mAlbum);
////                        return true;
//
//                    default:
//                        return true;
//                }
//            }
        };

        popupMenu.inflate(R.menu.post_menu_control);

//        if (mAlbum.isLocked()) {
//            popupMenu.getMenu().removeItem(R.id.album_overflow_lock);
//            popupMenu.getMenu().removeItem(R.id.album_overflow_rename);
//            popupMenu.getMenu().removeItem(R.id.album_overflow_delete);
//        } else {
//            popupMenu.getMenu().removeItem(R.id.album_overflow_unlock);
//        }

        popupMenu.show();
    }
}
