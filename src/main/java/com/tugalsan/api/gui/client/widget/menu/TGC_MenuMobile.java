package com.tugalsan.api.gui.client.widget.menu;

import com.google.gwt.user.client.ui.*;
import com.tugalsan.api.function.client.TGS_Func;
import java.util.*;
import com.tugalsan.api.log.client.*;
import com.tugalsan.api.gui.client.click.*;
import com.tugalsan.api.gui.client.dim.*;
import com.tugalsan.api.gui.client.pop.*;
import com.tugalsan.api.gui.client.widget.*;

import com.tugalsan.api.list.client.*;
import com.tugalsan.api.tuple.client.*;

public class TGC_MenuMobile {

    final private static TGC_Log d = TGC_Log.of(TGC_MenuMobile.class);

    public TGC_MenuMobile(TGC_Dimension dim, CharSequence fullIconClassName, CharSequence label, String ok, String esc) {
        pop = new TGC_PopLblYesNoListBox(
                dim, null, label, ok, esc,
                p -> {
                    p.getPop().setVisible(false);
                    var si = p.listBox.getSelectedIndex();
                    if (si == -1) {
                        d.ce(label, "Hata: Seçim yapılmadı hatası!");
                        return;
                    }
                    if (si < subMenus.size()) {
                        subMenus.get(si).run();
                    } else {
                        si -= subMenus.size();
                        cmd.get(si).value1.run();
                    }
                },
                p -> p.getPop().setVisible(false),
                null
        );
        widget = TGC_ButtonUtils.createIcon(fullIconClassName, label);
        TGC_ClickUtils.add(widget, () -> {
            reinitialize();
            pop.getPop().setVisible(true);
        });
        widget.setStyleName(TGC_MenuMobile.class.getSimpleName());
    }
    final TGC_PopLblYesNoListBox pop;
    final public PushButton widget;

    private void reinitialize() {
        pop.listBox.clear();
        subMenus.stream().forEachOrdered(o -> {
            o.reinitialize();
            pop.listBox.addItem(o.label);
        });
        cmd.stream().forEachOrdered(o -> pop.listBox.addItem(o.value0));
        TGC_ListBoxUtils.selectNone(pop.listBox);
    }

    final public List<TGC_MenuMobileSub> subMenus = TGS_ListUtils.of();
    final public List<TGS_Tuple2<String, TGS_Func>> cmd = TGS_ListUtils.of();
}
