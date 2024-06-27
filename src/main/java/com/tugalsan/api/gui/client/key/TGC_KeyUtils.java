package com.tugalsan.api.gui.client.key;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.tugalsan.api.function.client.TGS_Func;

import com.tugalsan.api.list.client.*;

public class TGC_KeyUtils {

//    final private static TGC_Log d = TGC_Log.of(TGC_KeyUtils.class);

    public static void addEsc(TGS_Func exe, FocusWidget... w) {
        TGS_ListUtils.of(w).forEach(wi -> wi.addKeyDownHandler(e -> {
            if (e.isAnyModifierKeyDown()) {
                return;
            }
            if (e.getNativeKeyCode() == KeyCodes.KEY_ESCAPE) {
                if (exe != null) {
                    exe.run();
                }
            }
        }));
    }

    public static void addCtrlEnter(TGS_Func exe, FocusWidget... w) {
        TGS_ListUtils.of(w).forEach(wi -> wi.addKeyDownHandler(e -> {
            if (e.isControlKeyDown() && e.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                if (exe != null) {
                    exe.run();
                }
            }
        }));
    }

    public static void addCtrlUp(TGS_Func exe, FocusWidget... w) {
        TGS_ListUtils.of(w).forEach(wi -> wi.addKeyDownHandler(e -> {
            if (e.isControlKeyDown() && e.getNativeKeyCode() == KeyCodes.KEY_UP) {
                if (exe != null) {
                    exe.run();
                }
            }
        }));
    }

    public static void addCtrlDown(TGS_Func exe, FocusWidget... w) {
        TGS_ListUtils.of(w).forEach(wi -> wi.addKeyDownHandler(e -> {
            if (e.isControlKeyDown() && e.getNativeKeyCode() == KeyCodes.KEY_DOWN) {
                if (exe != null) {
                    exe.run();
                }
            }
        }));
    }

    public static void addCtrlLeft(TGS_Func exe, FocusWidget... w) {
        TGS_ListUtils.of(w).forEach(wi -> wi.addKeyDownHandler(e -> {
            if (e.isControlKeyDown() && e.getNativeKeyCode() == KeyCodes.KEY_LEFT) {
                if (exe != null) {
                    exe.run();
                }
            }
        }));
    }

    public static void addCtrlRight(TGS_Func exe, FocusWidget... w) {
        TGS_ListUtils.of(w).forEach(wi -> wi.addKeyDownHandler(e -> {
            if (e.isControlKeyDown() && e.getNativeKeyCode() == KeyCodes.KEY_RIGHT) {
                if (exe != null) {
                    exe.run();
                }
            }
        }));
    }

    public static void addDel(TGS_Func exe, FocusWidget... w) {
        TGS_ListUtils.of(w).forEach(wi -> wi.addKeyDownHandler(e -> {
            if (e.getNativeKeyCode() == KeyCodes.KEY_DELETE) {
                if (exe != null) {
                    exe.run();
                }
            }
        }));
    }

    public static void add(PushButton btn, TGS_Func space, TGS_Func esc) {
        btn.addKeyDownHandler(e -> {
            if (e.isAnyModifierKeyDown()) {
                return;
            }
            var b = false;
            if (e.getNativeKeyCode() == KeyCodes.KEY_SPACE) {
                if (space != null) {
                    space.run();
                    b = true;
                }
            } else if (e.getNativeKeyCode() == KeyCodes.KEY_ESCAPE) {
                if (esc != null) {
                    esc.run();
                    b = true;
                }
            }
            if (b) {
                e.preventDefault();
            }
        });
    }

    public static void add(TextBox btn, TGS_Func enter, TGS_Func esc) {
        btn.addKeyDownHandler(e -> {
            if (e.isAnyModifierKeyDown()) {
                return;
            }
            var b = false;
            if (e.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                if (enter != null) {
                    enter.run();
                    b = true;
                }
            } else if (e.getNativeKeyCode() == KeyCodes.KEY_ESCAPE) {
                if (esc != null) {
                    esc.run();
                    b = true;
                }
            }
            if (b) {
                e.preventDefault();
            }
        });
    }

    public static void add(ListBox btn, TGS_Func enter, TGS_Func esc) {
        btn.addKeyDownHandler(e -> {
            if (e.isAnyModifierKeyDown()) {
                return;
            }
            switch (e.getNativeKeyCode()) {
                case KeyCodes.KEY_ENTER:
                case KeyCodes.KEY_SPACE:
                    if (enter != null) {
                        enter.run();
                    }
                    break;
                case KeyCodes.KEY_ESCAPE:
                    if (esc != null) {
                        esc.run();
                    }
                    break;
                default:
                    break;
            }
        });
    }

    public static void add(CheckBox btn, TGS_Func enter, TGS_Func esc) {
        btn.addKeyDownHandler(e -> {
            if (e.isControlKeyDown() || e.isShiftKeyDown() || e.isAltKeyDown()) {
                return;
            }
            switch (e.getNativeKeyCode()) {
                case KeyCodes.KEY_ENTER:
                    btn.setValue(!btn.getValue());//FIX
                    if (enter != null) {
                        enter.run();
                    }
                    break;
                case KeyCodes.KEY_SPACE:
                    if (enter != null) {
                        enter.run();
                    }
                    break;
                case KeyCodes.KEY_ESCAPE:
                    if (esc != null) {
                        esc.run();
                    }
                    break;
                default:
                    break;
            }
        });
    }

    public static void add(RadioButton btn, TGS_Func enter, TGS_Func esc) {
        add((CheckBox) btn, enter, esc);
    }

    public static void add(TextArea btn, TGS_Func controlEnter, TGS_Func esc) {
        btn.addKeyDownHandler(e -> {
            var b = false;
            if (e.getNativeKeyCode() == KeyCodes.KEY_ESCAPE) {
                if (e.isAnyModifierKeyDown()) {
                    return;
                }
                if (esc != null) {
                    esc.run();
                    b = true;
                }
            } else if (e.isControlKeyDown() && e.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                if (controlEnter != null) {
                    controlEnter.run();
                    b = true;
                }
            }
            if (b) {
                e.preventDefault();
            }
        });
    }

    public static boolean isControlKeyUp(KeyUpEvent e) {
        return e.isControlKeyDown();
    }

    public static boolean isControlKeyDown(KeyDownEvent e) {
        return e.isControlKeyDown();
    }

    public static boolean isShiftKeyUp(KeyUpEvent e) {
        return e.isShiftKeyDown();
    }

    public static boolean isShiftKeyDown(KeyDownEvent e) {
        return e.isShiftKeyDown();
    }

    public static boolean isKeyDown(KeyDownEvent e) {
        return e.getNativeKeyCode() == KeyCodes.KEY_DOWN;
    }

    public static boolean isKeyUp(KeyDownEvent e) {
        return e.getNativeKeyCode() == KeyCodes.KEY_UP;
    }

    public static boolean isKeyLeft(KeyDownEvent e) {
        return e.getNativeKeyCode() == KeyCodes.KEY_LEFT;
    }

    public static boolean isKeyRight(KeyDownEvent e) {
        return e.getNativeKeyCode() == KeyCodes.KEY_RIGHT;
    }
}
