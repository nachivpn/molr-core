/*
 * © Copyright 2016 CERN. This software is distributed under the terms of the Apache License Version 2.0, copied
 * verbatim in the file “COPYING“. In applying this licence, CERN does not waive the privileges and immunities granted
 * to it by virtue of its status as an Intergovernmental Organization or submit itself to any jurisdiction.
 */
package cern.molr.sample;

import java.util.ArrayList;
import java.util.List;

import cern.molr.commons.mission.Mission;

/**
 * Simple implementation of {@link Mission}
 *
 * @author tiagomr
 * @author mgalilee
 */
public class MissionImpl implements Mission {

    /**
     * Name of the mole that can execute the {@link Mission}
     */
    private String moleClassName;

    /**
     * Class used by the exposed {@link Mission}
     */
    private String missionContentClassName;

    /**
     * {@link List} of tasks exposed by this service in the specific class
     */
    private final List<String> tasks = new ArrayList<>();

    public MissionImpl() {
    }

    public MissionImpl(String moleClassName, String missionContentClassName) {
        this.moleClassName = moleClassName;
        this.missionContentClassName = missionContentClassName;
    }

    public MissionImpl(String moleClassName, String missionContentClassName, List<String> tasks) {
        this.moleClassName = moleClassName;
        this.missionContentClassName = missionContentClassName;
        this.tasks.addAll(tasks);
    }

    @Override
    public String getMoleClassName() {
        return moleClassName;
    }

    @Override
    public String getMissionDefnClassName() {
        return missionContentClassName;
    }

    public List<String> getTasksNames() {
        return new ArrayList<>(tasks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MissionImpl service = (MissionImpl) o;
        if (moleClassName != null ? !moleClassName.equals(service.moleClassName) : service.moleClassName != null)
            return false;
        return !(missionContentClassName != null ? !missionContentClassName.equals(service.missionContentClassName) : service.missionContentClassName != null);

    }

    @Override
    public int hashCode() {
        int result = moleClassName != null ? moleClassName.hashCode() : 0;
        result = 31 * result + (missionContentClassName != null ? missionContentClassName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getMoleClassName() + ": " + getMissionDefnClassName();
    }
}