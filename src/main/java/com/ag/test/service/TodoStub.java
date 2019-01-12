package com.ag.test.service;

import com.ag.test.api.model.ToDoItem;
import com.ag.test.api.model.ToDoItemAddRequest;
import com.ag.test.api.model.ToDoItemUpdateRequest;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TodoStub {

    private static Map<Long, ToDoItem> items = new HashMap<>();

    private static Long idIdx = 4l;

    static {
        ToDoItem item1 = new ToDoItem(1l, "I am a test item 1",true, Calendar.getInstance().getTime());
        ToDoItem item2 = new ToDoItem(2l, "I am a test item 2",true, Calendar.getInstance().getTime());
        ToDoItem item3 = new ToDoItem(3l, "I am a test item 3",true, Calendar.getInstance().getTime());
        items.put(1l, item1);
        items.put(2l, item2);
        items.put(3l, item3);
    }

    public static ToDoItem create(ToDoItemAddRequest request) {
        idIdx++;
        ToDoItem newItem = new ToDoItem(idIdx, request.getText(), true,Calendar.getInstance().getTime());
        items.put(idIdx, newItem);
        return newItem;
    }

    public static ToDoItem get(Long id) {
        return items.get(id);
    }

    public static ToDoItem modify(Long id, ToDoItemUpdateRequest update) {
        ToDoItem item = items.get(id);
        // update something
        item.setCreatedAt(Calendar.getInstance().getTime());
        item.setText(update.getText());
        item.setCompleted(update.getCompleted());
        items.put(id,item);
        return item;
    }

}
