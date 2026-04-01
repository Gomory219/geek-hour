package cn.edu.sxu.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RollQueryResponse<T> {
    private List<T> data;

    public static<T> RollQueryResponse<T> fromData(List<T> data) {
        return new RollQueryResponse<>(data);
    }
}
