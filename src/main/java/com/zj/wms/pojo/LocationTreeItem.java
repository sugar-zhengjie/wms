package com.zj.wms.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LocationTreeItem {
    private String title;
    private String id;
    private String no;
    private String lay;
    private List<LocationTreeItem> children;
}
