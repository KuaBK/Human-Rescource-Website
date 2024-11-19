import React, { useEffect, useState } from 'react';
import ReactApexChart from 'react-apexcharts';

const EmployeesInfo = () => {

    const [chartData, setChartData] = useState({
        series: [{
            name: 'Employee Attendance',
            data: [30, 40, 35, 50, 49, 60, 70]  // Example data, can be dynamic
        }],
        options: {
            chart: {
                type: 'line',
                height: 350
            },
            xaxis: {
                categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul']  // Example months
            },
            title: {
                text: 'Employee Analytics',
                align: 'center'
            },
            colors: ['#00E396'] // Custom color for the chart line
        }
    });

    return (
        <div className="col-md-12">
            <div className="card">
                <div className="card-header py-3 d-flex justify-content-between bg-transparent border-bottom-0">
                    <h6 className="mb-0 fw-bold">Employees Info</h6>
                </div>
                <div className="card-body">
                    <div className="ac-line-transparent">
                        <ReactApexChart
                            options={chartData.options}
                            series={chartData.series}
                            type="line"
                            height={200}
                        />
                    </div>
                </div>
            </div>
        </div>
    );
};

export default EmployeesInfo;
