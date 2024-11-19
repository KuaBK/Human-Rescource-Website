import React from "react";

const InterviewAndHiredStats = () => {
    return (
        <div className="row g-3 row-deck">
            {/* Interviews Card */}
            <div className="col-md-6 col-lg-6 col-xl-12">
                <div className="card mb-3">
                    <div className="card-body">
                        <div className="d-flex align-items-center flex-fill">
                            <span className="avatar lg light-success-bg rounded-circle text-center d-flex align-items-center justify-content-center">
                                <i className="icofont-users-alt-2 fs-5"></i>
                            </span>
                            <div className="d-flex flex-column ps-3 flex-fill">
                                <h6 className="fw-bold mb-0 fs-4">246</h6>
                                <span className="text-muted">Interviews</span>
                            </div>
                            <i className="icofont-chart-bar-graph fs-3 text-muted"></i>
                        </div>
                    </div>
                </div>
            </div>

            {/* Hired Card */}
            <div className="col-md-6 col-lg-6 col-xl-12">
                <div className="card">
                    <div className="card-body">
                        <div className="d-flex align-items-center flex-fill">
                            <span className="avatar lg light-success-bg rounded-circle text-center d-flex align-items-center justify-content-center">
                                <i className="icofont-holding-hands fs-5"></i>
                            </span>
                            <div className="d-flex flex-column ps-3 flex-fill">
                                <h6 className="fw-bold mb-0 fs-4">101</h6>
                                <span className="text-muted">Hired</span>
                            </div>
                            <i className="icofont-chart-line fs-3 text-muted"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default InterviewAndHiredStats;
