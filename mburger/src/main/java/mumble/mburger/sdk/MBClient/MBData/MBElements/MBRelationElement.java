package mumble.mburger.sdk.MBClient.MBData.MBElements;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBClient.MBData.MBAtomic.MBClass;

/**
 * Identifies a relation element with a block_id and a section_id
 *
 * @author  Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBRelationElement extends MBClass {

    /**Content of the text*/
    private long block_id;
    private long section_id;

    public MBRelationElement(long id, String name, long block_id, long section_id) {
        this.block_id = block_id;
        this.section_id = section_id;
        initialize(id, name, MBConstants.type_relation);
    }

    @Override
    public void initialize(long id, String name, String type) {
        super.setId(id);
        super.setName(name);
        super.setType(type);
    }

    public long getBlock_id() {
        return block_id;
    }

    public long getSection_id() {
        return section_id;
    }
}
