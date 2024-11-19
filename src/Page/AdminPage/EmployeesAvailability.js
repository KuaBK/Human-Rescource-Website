import React from "react";

const availabilityStats = [
    { icon: "icofont-checked", title: "Attendance", count: 400 },
    { icon: "icofont-stopwatch", title: "Late Coming", count: 17 },
    { icon: "icofont-ban", title: "Absent", count: 6 },
    { icon: "icofont-beach-bed", title: "Leave Apply", count: 14 },
];

const EmployeesAvailability = () => {
    return (
        <div className="col-md-6">
            <div className="card">
                <div className="card-header py-3 d-flex justify-content-between bg-transparent border-bottom-0">
                    <h6 className="mb-0 fw-bold">Employees Availability</h6>
                </div>
                <div className="card-body">
                    <div className="row g-2 row-deck">
                        {availabilityStats.map((item, index) => (
                            <div className="col-md-6 col-sm-6" key={index}>
                                <div className="card">
                                    <div className="card-body">
                                        <i className={`${item.icon} fs-3`}></i>
                                        <h6 className="mt-3 mb-0 fw-bold small-14">{item.title}</h6>
                                        <span className="text-muted">{item.count}</span>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
        </div>
    );
};

export default EmployeesAvailability;