/*
 * © Copyright 2016 CERN. This software is distributed under the terms of the Apache License Version 2.0, copied
 * verbatim in the file “COPYING“. In applying this licence, CERN does not waive the privileges and immunities granted
 * to it by virtue of its status as an Intergovernmental Organization or submit itself to any jurisdiction.
 */

package cern.molr.exception;

/**
 *
 * @author timartin
 */
public class MissionExecutionException extends Exception {

    public MissionExecutionException(String message) {
        super(message);
    }

    public MissionExecutionException(Throwable cause) {
        super(cause);
    }

    public MissionExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
