package xyz.luan.games.hangman.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.Arrays;

import lombok.SneakyThrows;
import xyz.luan.reflection.ReflectionUtils;

public class AccessibleObject {

	@Access(AccessLevel.NEVER)
	private AccessLevel level;

	public void setLevel(AccessLevel level) {
		this.level = level;
	}

	private void writeObject(ObjectOutputStream stream) {
		writeValue(stream, level);
		ReflectionUtils.consumeFieldsRecursively(this.getClass(), (f) -> {
			if (access(f)) {
				writeValue(stream, f.get(this));
			}
		});
	}

	private boolean access(Field f) {
		Access access = f.getAnnotation(Access.class);
		return access == null || Arrays.asList(access).contains(level);
	}

	@SneakyThrows
	private static void writeValue(ObjectOutputStream stream, Object value) {
		stream.writeObject(value);
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		level = (AccessLevel) readValue(stream);
		ReflectionUtils.consumeFieldsRecursively(this.getClass(), (f) -> {
			if (access(f)) {
				f.set(this, readValue(stream));
			}
		});
	}

	@SneakyThrows
	private Object readValue(ObjectInputStream stream) {
		return stream.readObject();
	}

	public static void serialize(AccessLevel level, ObjectOutputStream stream, AccessibleObject object) throws IOException {
		object.setLevel(level);
		stream.writeObject(object);
	}

	public static AccessibleObject deserialize(ObjectInputStream stream) throws ClassNotFoundException, IOException {
		return (AccessibleObject) stream.readObject();
	}
}
