package com.ipldashboard.response;

import com.ipldashboard.model.MatchEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
public class MatchResponse implements Serializable {

    private List<MatchEntity> matches;

    private int firstYearOfTeamInData;

    private int lastYearOfTeamInData;
}
