import React from "react";
import './UpcomingInterviews.scss'

const interviewees = [
    { name: "Natalie Gibson", role: "Ui/UX Designer", time: "1.30 - 1:30" },
    { name: "Peter Piperg", role: "Web Design", time: "9.00 - 1:30" },
    { name: "Robert Young", role: "PHP Developer", time: "1.30 - 2:30" },
    { name: "Victoria Vbell", role: "IOS Developer", time: "2.00 - 3:30" },
];


const UpcomingInterviews = () => {
    return (
        <div className="card">
            <div className="card-header py-3 d-flex justify-content-between bg-transparent border-bottom-0">
                <h6 className="mb-0 fw-bold">Upcoming Interviews</h6>
            </div>
            <div className="card-body" style={{ minHeight: '700px' }}>
                {interviewees.map((item, index) => (
                    <div className="py-2 d-flex align-items-center border-bottom flex-wrap" key={index}>
                        <div className="d-flex align-items-center flex-fill">
                            {/* Chèn ảnh ngẫu nhiên từ một dịch vụ tạo ảnh */}
                            <img
                                className="avatar lg rounded-circle img-thumbnail"
                                src={`https://robohash.org/${item.name.replace(/\s+/g, '')}?set=set1&size=120x120`}
                                alt="profile"
                            />
                            <div className="d-flex flex-column ps-3">
                                <h6 className="fw-bold mb-0 small-14">{item.name}</h6>
                                <span className="text-muted">{item.role}</span>
                            </div>
                        </div>
                        <div className="time-block text-truncate">
                            <i className="icofont-clock-time"></i> {item.time}
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default UpcomingInterviews;
