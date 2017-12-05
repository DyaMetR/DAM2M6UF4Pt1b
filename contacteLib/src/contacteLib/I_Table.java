package contacteLib;

/**
 *
 * @author DyaMetR
 */
public interface I_Table<T> {
    public T[] select();
    public void insert(T object);
    public void delete(T object);
}
