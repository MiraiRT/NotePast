import java.util.List;

public class LocationTag extends Tag{
    public LocationTag(String place){
        super.tagName = "@" + place;
    }

    @Override
    public String getTagName() {
        return super.getTagName();
    }

    @Override
    public void setTagName(String tagName) {
        super.setTagName(tagName);
    }

    @Override
    public List<Note> getStackOfNote() {
        return super.getStackOfNote();
    }

    @Override
    public void setStackOfNote(List<Note> stackOfNote) {
        super.setStackOfNote(stackOfNote);
    }

    @Override
    public Tag addTag(User user,Note note,String name){
        LocationTag newTag = new LocationTag(name);
//        note.getNoteTag().add(newTag);
//        user.getStackOfLocationTag().add(newTag);
        return newTag;
    }

    @Override
    public boolean isTagInList(User user, int noteID) {
//        int index = 0;
//        if (index < user.getStackOfLocationTag().size()){
//            for(LocationTag i : user.getStackOfLocationTag()){
//                if(index >= user.getStackOfLocationTag().size()){
//                    return false;
//                }
//                if(i.getStackOfNote().get(index).getNoteIDGenerator() == noteID) {
//                    return true;
//                }
//                index++;
//            }
//        }
        return false;
    }

    @Override
    public void deleteTag() {

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
