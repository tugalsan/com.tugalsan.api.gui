package com.tugalsan.api.gui.client.focus;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBoxBase;
import com.tugalsan.api.callable.client.TGS_CallableType1Void;

import com.tugalsan.api.log.client.TGC_Log;
import com.tugalsan.api.thread.client.TGC_ThreadUtils;

public class TGC_FocusUtils {

    final private static TGC_Log d = TGC_Log.of(TGC_FocusUtils.class);

    public static void setFocusAfterGUIUpdate(FocusWidget fw) {
        TGC_ThreadUtils.run_afterGUIUpdate(() -> {
            fw.setFocus(true);
            if (fw instanceof TextArea) {
                //DO NOTHING
            } else if (fw instanceof ValueBoxBase) {
                var vb = (ValueBoxBase) fw;
                if (!vb.isReadOnly()) {
                    vb.selectAll();
                }
            }
        });
    }

    //SIDES LOGIC DRIVER
    public static void focusSideLeft(FocusWidget fw, TGS_FocusSides4 sides) {
        if (fw instanceof TextBox) {//SKIP - inclusdes PasswordTextBox and DateBox
            var tb = (TextBox) fw;
            if (tb.getText().length() != 0 && tb.getCursorPos() != 0) {
                return;
            }
        }
        if (sides != null && sides.left != null && sides.left.isVisible()) {
            sides.left.setFocus(true);
        }
    }

    public static void focusSideRight(FocusWidget fw, TGS_FocusSides4 sides) {
        if (fw instanceof TextBox) {//SKIP - inclusdes PasswordTextBox and DateBox
            var tb = (TextBox) fw;
            if (tb.getText().length() != 0 && tb.getCursorPos() != tb.getText().length()) {
                return;
            }
        }
        if (sides != null && sides.right != null && sides.right.isVisible()) {
            sides.right.setFocus(true);
        }
    }

    public static void focusSideUp(FocusWidget fw, TGS_FocusSides4 sides) {
        if (fw instanceof ListBox) {//SKIP
            var tb = (ListBox) fw;
            if (tb.getItemCount() != 0 && tb.getSelectedIndex() != -1 && tb.getSelectedIndex() != 0) {
                return;
            }
        }
        if (sides != null && sides.up != null && sides.up.isVisible()) {
            sides.up.setFocus(true);
        }
    }

    public static void focusSideDown(FocusWidget fw, TGS_FocusSides4 sides) {
        if (fw instanceof ListBox) {//SKIP
            var tb = (ListBox) fw;
            if (tb.getItemCount() != 0 && tb.getSelectedIndex() != -1 && tb.getSelectedIndex() != tb.getItemCount() - 1) {
                return;
            }
        }
        if (sides != null && sides.down != null && sides.down.isVisible()) {
            sides.down.setFocus(true);
        }
    }

    public static void focusSide(FocusWidget fw, TGS_FocusSides4 sides, int nativeKeyCode) {
        switch (nativeKeyCode) {
            case KeyCodes.KEY_UP:
                d.ci("KeyUpHandler", "KeyCodes.KEY_UP");
                if (fw instanceof TextArea) {
                    var ta = (TextArea) fw;
                    var pos0 = ta.getCursorPos();
                    TGC_ThreadUtils.run_afterGUIUpdate(() -> {
                        var pos1 = ta.getCursorPos();
                        if (pos0 == pos1) {
                            d.ci("pos", "posizyon değişmedi, focus up");
                            focusSideUp(fw, sides);
                        } else {
                            d.ci("pos", "posizyon değişti, skip");
                        }
                    });
                } else {
                    focusSideUp(fw, sides);
                }
                break;
            case KeyCodes.KEY_DOWN:
                d.ci("KeyUpHandler", "KeyCodes.KEY_DOWN");
                focusSideDown(fw, sides);
                break;
            case KeyCodes.KEY_LEFT:
                d.ci("KeyUpHandler", "KeyCodes.KEY_LEFT");
                focusSideLeft(fw, sides);
                break;
            case KeyCodes.KEY_RIGHT:
                d.ci("KeyUpHandler", "KeyCodes.KEY_RIGHT");
                focusSideRight(fw, sides);
                break;
            default:
                break;
        }
    }

    @Deprecated //problem on textFields
    public static void addKeyUp(FocusWidget fw, TGS_FocusSides4 sides) {
        fw.addKeyUpHandler(e -> {
            var curKeyCode = e.getNativeKeyCode();
            d.ci("KeyUpHandler", "curKeyCode", curKeyCode);
            focusSide(fw, sides, curKeyCode);
        });
    }

    public static void addKeyDown(FocusWidget fw, TGS_FocusSides4 sides) {
        fw.addKeyDownHandler(e -> {
            var curKeyCode = e.getNativeKeyCode();
            d.ci("KeyUpHandler", "curKeyCode", curKeyCode);
            focusSide(fw, sides, curKeyCode);
        });
    }

    @Deprecated //problem on textFields
    public static void addKeyUp(FocusWidget fw, TGS_CallableType1Void<Integer> exe) {
        fw.addKeyUpHandler(e -> {
            var curKeyCode = e.getNativeKeyCode();
            d.ci("KeyUpHandler", "curKeyCode", curKeyCode);
            exe.run(curKeyCode);
        });
    }

    public static void addKeyDown(FocusWidget fw, TGS_CallableType1Void<Integer> exe) {
        fw.addKeyDownHandler(e -> {
            var curKeyCode = e.getNativeKeyCode();
            d.ci("KeyUpHandler", "curKeyCode", curKeyCode);
            exe.run(curKeyCode);
        });
    }

    public static void addKey(ListBox lb, TGS_FocusSides4 sides, TGS_CallableType1Void<Integer> exe) {
        var sideUp = sides.up;
        var sideDown = sides.down;
        sides.up = sides.down = null;
        TGC_FocusUtils.addKeyDown(lb, sides);
        TGC_FocusUtils.addKeyUp(lb, nativeKeyCode -> {
            if (exe == null) {
                return;
            }
            switch (nativeKeyCode) {
                case KeyCodes.KEY_UP:
                case KeyCodes.KEY_DOWN:
                    exe.run(lb.getSelectedIndex());
                    break;
                default:
                    break;
            }
        });
        TGC_FocusUtils.addKeyDown(lb, nativeKeyCode -> {
            if (null != nativeKeyCode) {
                switch (nativeKeyCode) {
                    case KeyCodes.KEY_UP:
                        if (sideUp == null) {
                            return;
                        }
                        if (lb.getItemCount() == 0 || lb.getSelectedIndex() == 0) {
                            TGC_FocusUtils.setFocusAfterGUIUpdate(sideUp);
                            if (sideUp instanceof TextBox) {
                                var tb = (TextBox) sideUp;
                                tb.selectAll();
                            }
                        }
                        break;
                    case KeyCodes.KEY_DOWN:
                        if (sideDown == null) {
                            return;
                        }
                        if (lb.getItemCount() == 0 || lb.getSelectedIndex() == lb.getItemCount() - 1) {
                            TGC_FocusUtils.setFocusAfterGUIUpdate(sideDown);
                            if (sideDown instanceof TextBox) {
                                var tb = (TextBox) sideDown;
                                tb.selectAll();
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
