package gr.kotsovolos.integration.pim.message.transformer;

public interface Transformer<T, V> {

	V transform(T t);
}
