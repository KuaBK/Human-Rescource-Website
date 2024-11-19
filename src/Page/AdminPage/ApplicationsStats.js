import React from "react";
import Icofont from 'react-icofont';


const ApplicationsStats = () => {
    return (
        <div className="row g-3 row-deck">
            <div className="col-md-6 col-lg-6 col-xl-12">
                <div className="card bg-primary">
                    <div className="card-body row">
                        <div className="col">
                            <span className="avatar lg bg-white rounded-circle text-center d-flex align-items-center justify-content-center">
                                <i className="icofont-file-text fs-5"></i>
                            </span>
                            <h1 className="mt-3 mb-0 fw-bold text-white">1546</h1>
                            <span className="text-white">Applications</span>
                        </div>
                        <div className="col">
                            <img
                                className="img-fluid"
                                src="/interview.svg"
                                alt="interview"
                            />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ApplicationsStats;
