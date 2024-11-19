import React, { useState } from "react";
import Chart from "react-apexcharts";

const TotalEmployees = () => {
    // Dữ liệu biểu đồ
    const [chartData] = useState({
        series: [60, 40], // 60% Nam, 40% Nữ
        options: {
            chart: {
                type: "pie",
            },
            labels: ["Male", "Female"],
            colors: ["#1E90FF", "#FF69B4"], // Màu cho nam và nữ
            legend: {
                position: "bottom",
            },
            dataLabels: {
                enabled: true,
                formatter: (val) => `${val.toFixed(1)}%`,
            },
        },
    });

    return (
        <div className="col-md-6">
            <div className="card">
                <div className="card-header py-3 d-flex justify-content-between bg-transparent border-bottom-0">
                    <h6 className="mb-0 fw-bold">Gender Distribution</h6>
                    <h4 className="mb-0 fw-bold">423 Employees</h4>
                </div>
                <div className="card-body">
                    <div className="mt-3">
                        <Chart
                            options={chartData.options}
                            series={chartData.series}
                            type="pie"
                            height={225}
                        />
                    </div>
                </div>
            </div>
        </div>
    );
};

export default TotalEmployees;
