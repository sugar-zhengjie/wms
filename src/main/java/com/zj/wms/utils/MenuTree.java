package com.zj.wms.utils;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@ToString
public class MenuTree {
    private Integer id;
    private String title;
    private boolean checked;
    private boolean spread;
    private List<MenuTree> children = new ArrayList<>();
}
