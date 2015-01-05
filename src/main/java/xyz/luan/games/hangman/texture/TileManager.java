package xyz.luan.games.hangman.texture;

import java.io.InputStream;

import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import xyz.luan.games.hangman.game.ConfigManager;

public final class TileManager {

	private TileManager() {
		throw new RuntimeException("Should not be instanciated");
	}

	public static Image createTile(TileType type, InputStream stream) {
		return new Image(stream, type.getSize(), type.getSize(), false, true);
	}

	public static Image createTile(TileType type, String path) {
		return new Image(path, type.getSize(), type.getSize(), false, true);
	}

	@AllArgsConstructor
	public enum TileType {
		SPELL(Size.BIG),
		AVATAR(Size.BIG),
		LETTER(Size.SMALL);

		private Size size;

		public int getSize() {
			return size.getSize();
		}

		private enum Size {
			SMALL {
				@Override
				public int getSize() {
					return ConfigManager.client.config().screen().getSmallTileSize();
				}
			},
			BIG {
				@Override
				public int getSize() {
					return ConfigManager.client.config().screen().getBigTileSize();
				}
			};

			public abstract int getSize();
		}
	}
}
