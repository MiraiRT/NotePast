import java.util.List;

public class PeopleTag extends Tag {
    public PeopleTag(String people){
        super.tagName = "#" + people;
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
        PeopleTag newTag = new PeopleTag(name);
//        note.getNoteTag().add(newTag);
//        user.getStackOfPeopleTag().add(newTag);
        return newTag;
    }

    @Override
    public boolean isTagInList(User user, int noteID) {
        int index = 0;
//        if (index < user.getStackOfPeopleTag().size()){
//            for(PeopleTag i : user.getStackOfPeopleTag()){
//                if(index >= user.getStackOfPeopleTag().size()){
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
        return "Tag: " + this.tagName + "\n"
                + "Note: " + super.getStackOfNote() + "\n";
    }
}
