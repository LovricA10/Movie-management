/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.helper;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;
import static javax.swing.TransferHandler.MOVE;
import javax.swing.TransferHandler.TransferSupport;

/**
 *
 * @author Lovric
 */
public class GenericTransferable<T> implements Transferable {

    public static final DataFlavor DATA_FLAVOR = new DataFlavor(Object.class, "Generic Object");

    private final T object;

    public GenericTransferable(T object) {
        this.object = object;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{DATA_FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return DATA_FLAVOR.equals(flavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (!isDataFlavorSupported(flavor)) {
            throw new UnsupportedFlavorException(flavor);
        }
        return object;
    }

    public static <T> TransferHandler createTransferHandler(java.util.function.Consumer<T> onDrop) {
        return new TransferHandler() {
            @Override
            public int getSourceActions(JComponent c) {
                return MOVE;
            }

            @Override
            protected Transferable createTransferable(JComponent c) {
                @SuppressWarnings("unchecked")
                JList<T> list = (JList<T>) c;
                return new GenericTransferable<>(list.getSelectedValue());

            }

            @Override
            public boolean canImport(TransferSupport support) {
                return support.isDataFlavorSupported(DATA_FLAVOR);
            }

            @Override
            public boolean importData(TransferSupport support) {
                if (!canImport(support)) {
                    return false;
                }
                try {
                    @SuppressWarnings("unchecked")
                    T data = (T) support.getTransferable().getTransferData(DATA_FLAVOR);
                    System.out.println("DEBUG: Dropped item:" + data);
                    onDrop.accept(data);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        };
    }
}
