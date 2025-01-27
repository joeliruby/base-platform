import os

source_dir = 'src/main/java/com/matariky'
test_dir = 'src/test/java/com/matariky'

def create_test_file(package, class_name):
    test_class_name = class_name + 'Test'
    test_file_path = os.path.join('src/test/java/', package.replace('.', '/'), test_class_name + '.java')
    os.makedirs(os.path.dirname(test_file_path), exist_ok=True)

    with open(test_file_path, 'w') as test_file:
        test_file.write(f'package {package};\n\n')
        test_file.write(f'import static org.junit.jupiter.api.Assertions.*;\n')
        test_file.write(f'import static org.mockito.Mockito.*;\n\n')
        test_file.write(f'import org.junit.jupiter.api.BeforeEach;\n')
        test_file.write(f'import org.junit.jupiter.api.Test;\n')
        test_file.write(f'import org.mockito.InjectMocks;\n')
        test_file.write(f'import org.mockito.MockitoAnnotations;\n')
        test_file.write(f'import org.springframework.boot.test.context.SpringBootTest;\n\n')
        test_file.write(f'@SpringBootTest\n')
        test_file.write(f'public class {test_class_name} {{\n\n')
        test_file.write(f'    @InjectMocks\n')
        test_file.write(f'    private {class_name} {class_name.lower()};\n\n')
        test_file.write(f'    @BeforeEach\n')
        test_file.write(f'    void setUp() {{\n')
        test_file.write(f'        MockitoAnnotations.openMocks(this);\n')
        test_file.write(f'    }}\n\n')
        test_file.write(f'    @Test\n')
        test_file.write(f'    void testSomeMethod() {{\n')
        test_file.write(f'        // Given\n')
        test_file.write(f'        // Initialize your test data and mocks here\n\n')
        test_file.write(f'        // When\n')
        test_file.write(f'        // Call the method you want to test\n\n')
        test_file.write(f'        // Then\n')
        test_file.write(f'        // Assert the expected results\n')
        test_file.write(f'        assertThat({class_name.lower()}.someMethod()).isEqualTo(expectedValue);\n')
        test_file.write(f'    }}\n\n')
        test_file.write(f'    // Add more test methods for other methods in {class_name}\n')
        test_file.write(f'}}\n')

def process_directory(directory, package):
    for filename in os.listdir(directory):
        filepath = os.path.join(directory, filename)
        if os.path.isdir(filepath):
            process_directory(filepath, f'{package}.{filename}')
        elif filename.endswith('.java'):
            class_name = filename[:-5]
            create_test_file(package, class_name)

if __name__ == '__main__':
    base_package = 'com.matarikyr'
    process_directory(source_dir, base_package)
    print('Test classes generated successfully.')