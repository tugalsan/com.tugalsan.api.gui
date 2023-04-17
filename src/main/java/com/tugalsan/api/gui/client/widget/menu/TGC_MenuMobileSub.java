package com.tugalsan.api.gui.client.widget.menu;

import java.util.*;
import com.tugalsan.api.log.client.*;
import com.tugalsan.api.gui.client.dim.*;
import com.tugalsan.api.gui.client.pop.*;
import com.tugalsan.api.gui.client.widget.*;
import com.tugalsan.api.runnable.client.*;
import com.tugalsan.api.list.client.*;
import com.tugalsan.api.pack.client.*;

public class TGC_MenuMobileSub implements TGS_Runnable {

    final private static TGC_Log d = TGC_Log.of(TGC_MenuMobileSub.class);

    public TGC_MenuMobileSub(TGC_Dimension dim, CharSequence label, String ok, String esc) {
        this.label = label.toString();
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
    }
    final TGC_PopLblYesNoListBox pop;
    final String label;

    public void reinitialize() {
        pop.listBox.clear();
        subMenus.stream().forEachOrdered(o -> {
            o.reinitialize();
            pop.listBox.addItem(o.label);
        });
        cmd.stream().forEachOrdered(o -> pop.listBox.addItem(o.value0));
        TGC_ListBoxUtils.selectNone(pop.listBox);
    }
    final public List<TGC_MenuMobileSub> subMenus = TGS_ListUtils.of();
    final public List<TGS_Pack2<String, TGS_Runnable>> cmd = TGS_ListUtils.of();

    @Override
    public void run() {
        TGC_ListBoxUtils.selectNone(pop.listBox);
        pop.getPop().setVisible(true);
    }
}
