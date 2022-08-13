package com.jcduhdt.sharp.codeposition.util;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public class SystemUtil {

    public static String getTextFromClipBoard() {
        //获取系统剪切板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        Transferable content = clipboard.getContents(null);//从系统剪切板中获取数据
        String text = "";//从数据中获取文本值

        if (content.isDataFlavorSupported(DataFlavor.stringFlavor)) {//判断是否为文本类型
            try {
                text = (String) content.getTransferData(DataFlavor.stringFlavor);
            } catch (Exception ex) {
                ex.printStackTrace();
                return "";
            }
        }
        return text;
    }
}
