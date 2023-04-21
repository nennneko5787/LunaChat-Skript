package ml.nennneko5787.minecraft.LunaChatSkript.skript.type;

import com.github.ucchyocean.lc3.japanize.JapanizeType;

public class JapanizeTypeType {

    private final JapanizeType type;

    public JapanizeTypeType(String type) {
		this.type = JapanizeType.fromID(type, JapanizeType.NONE);
    }

    public JapanizeType gettype() {
        return this.type;
    }
}