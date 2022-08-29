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
import com.tugalsan.api.executable.client.TGS_Executable;
import com.tugalsan.api.list.client.*;
import com.tugalsan.api.log.client.TGC_Log;

public class TGC_KeyUtils {

    final private static TGC_Log d = TGC_Log.of(TGC_KeyUtils.class);

    public static void addEsc(TGS_Executable exe, FocusWidget... w) {
        TGS_ListUtils.of(w).forEach(wi -> wi.addKeyDownHandler(e -> {
            if (e.isAnyModifierKeyDown()) {
                return;
            }
            if (e.getNativeKeyCode() == KeyCodes.KEY_ESCAPE) {
                if (exe != null) {
                    exe.execute();
                }
            }
        }));
    }

    public static void addCtrlEnter(TGS_Executable exe, FocusWidget... w) {
        TGS_ListUtils.of(w).forEach(wi -> wi.addKeyDownHandler(e -> {
            if (e.isControlKeyDown() && e.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                if (exe != null) {
                    exe.execute();
                }
            }
        }));
    }

    public static void addCtrlUp(TGS_Executable exe, FocusWidget... w) {
        TGS_ListUtils.of(w).forEach(wi -> wi.addKeyDownHandler(e -> {
            if (e.isControlKeyDown() && e.getNativeKeyCode() == KeyCodes.KEY_UP) {
                if (exe != null) {
                    exe.execute();
                }
            }
        }));
    }

    public static void addCtrlDown(TGS_Executable exe, FocusWidget... w) {
        TGS_ListUtils.of(w).forEach(wi -> wi.addKeyDownHandler(e -> {
            if (e.isControlKeyDown() && e.getNativeKeyCode() == KeyCodes.KEY_DOWN) {
                if (exe != null) {
                    exe.execute();
                }
            }
        }));
    }

    public static void addCtrlLeft(TGS_Executable exe, FocusWidget... w) {
        TGS_ListUtils.of(w).forEach(wi -> wi.addKeyDownHandler(e -> {
            if (e.isControlKeyDown() && e.getNativeKeyCode() == KeyCodes.KEY_LEFT) {
                if (exe != null) {
                    exe.execute();
                }
            }
        }));
    }

    public static void addCtrlRight(TGS_Executable exe, FocusWidget... w) {
        TGS_ListUtils.of(w).forEach(wi -> wi.addKeyDownHandler(e -> {
            if (e.isControlKeyDown() && e.getNativeKeyCode() == KeyCodes.KEY_RIGHT) {
                if (exe != null) {
                    exe.execute();
                }
            }
        }));
    }

    public static void addDel(TGS_Executable exe, FocusWidget... w) {
        TGS_ListUtils.of(w).forEach(wi -> wi.addKeyDownHandler(e -> {
            if (e.getNativeKeyCode() == KeyCodes.KEY_DELETE) {
                if (exe != null) {
                    exe.execute();
                }
            }
        }));
    }

    public static void add(PushButton btn, TGS_Executable space, TGS_Executable esc) {
        btn.addKeyDownHandler(e -> {
            if (e.isAnyModifierKeyDown()) {
                return;
            }
            boolean b = false;
            if (e.getNativeKeyCode() == KeyCodes.KEY_SPACE) {
                if (space != null) {
                    space.execute();
                    b = true;
                }
            } else if (e.getNativeKeyCode() == KeyCodes.KEY_ESCAPE) {
                if (esc != null) {
                    esc.execute();
                    b = true;
                }
            }
            if (b) {
                e.preventDefault();
            }
        });
    }

    public static void add(TextBox btn, TGS_Executable enter, TGS_Executable esc) {
        btn.addKeyDownHandler(e -> {
            if (e.isAnyModifierKeyDown()) {
                return;
            }
            boolean b = false;
            if (e.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                if (enter != null) {
                    enter.execute();
                    b = true;
                }
            } else if (e.getNativeKeyCode() == KeyCodes.KEY_ESCAPE) {
                if (esc != null) {
                    esc.execute();
                    b = true;
                }
            }
            if (b) {
                e.preventDefault();
            }
        });
    }

    public static void add(ListBox btn, TGS_Executable enter, TGS_Executable esc) {
        btn.addKeyDownHandler(e -> {
            if (e.isAnyModifierKeyDown()) {
                return;
            }
            switch (e.getNativeKeyCode()) {
                case KeyCodes.KEY_ENTER:
                case KeyCodes.KEY_SPACE:
                    if (enter != null) {
                        enter.execute();
                    }
                    break;
                case KeyCodes.KEY_ESCAPE:
                    if (esc != null) {
                        esc.execute();
                    }
                    break;
                default:
                    break;
            }
        });
    }

    public static void add(CheckBox btn, TGS_Executable enter, TGS_Executable esc) {
        btn.addKeyDownHandler(e -> {
            if (e.isControlKeyDown() || e.isShiftKeyDown() || e.isAltKeyDown()) {
                return;
            }
            switch (e.getNativeKeyCode()) {
                case KeyCodes.KEY_ENTER:
                    btn.setValue(!btn.getValue());//FIX
                    if (enter != null) {
                        enter.execute();
                    }
                    break;
                case KeyCodes.KEY_SPACE:
                    if (enter != null) {
                        enter.execute();
                    }
                    break;
                case KeyCodes.KEY_ESCAPE:
                    if (esc != null) {
                        esc.execute();
                    }
                    break;
                default:
                    break;
            }
        });
    }

    public static void add(RadioButton btn, TGS_Executable enter, TGS_Executable esc) {
        add((CheckBox) btn, enter, esc);
    }

    public static void add(TextArea btn, TGS_Executable controlEnter, TGS_Executable esc) {
        btn.addKeyDownHandler(e -> {
            boolean b = false;
            if (e.getNativeKeyCode() == KeyCodes.KEY_ESCAPE) {
                if (e.isAnyModifierKeyDown()) {
                    return;
                }
                if (esc != null) {
                    esc.execute();
                    b = true;
                }
            } else if (e.isControlKeyDown() && e.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                if (controlEnter != null) {
                    controlEnter.execute();
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
