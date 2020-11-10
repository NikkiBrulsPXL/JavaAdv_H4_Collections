package be.pxl.ja.streamingservice;

import be.pxl.ja.ContentRepository;
import be.pxl.ja.streamingservice.model.Content;

import java.util.HashSet;
import java.util.List;

public class ContentRepositoryApp {
    public static void main(String[] args) {
        ContentRepository repo = new ContentRepository();
        List<Content> list = repo.getContentList();
        System.out.println(list.getClass());
        for (Content c : list) {
            System.out.println(c.toString());
        }
        HashSet<Content> hashSet = repo.getContentSet();
        System.out.println(hashSet.getClass());
        for (Content c : hashSet) {
            System.out.println(c.toString());
        }
    }
}
